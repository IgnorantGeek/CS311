import java.lang.*;
import java.util.*;
import java.net.*;
import java.io.*;

/**
NEED TO ADD CODE TO DELAY REQUESTS FROM WIKI SERVER
*/

class WikiCrawler
{
  //crawl through wiki
  PriorityQ PQ = new PriorityQ();
  int priority = Integer.MAX_VALUE;
  int Max;
  int numLinks = 1;
  int req;
  String[] Topics;
  String[] Discovered; //stores the relative addresses of all discovered links
  String Seed;
  String Output;
  String writeContent; //stores the content to write to file? Set delimiter as a space, write two items then new line
  static final String BASE_URL = "https://en.wikipedia.org";
  WikiCrawler(String seed, int max, String[] topics, String output)
  {
    //initializes wikicrawler
    Topics = topics;
    Seed = seed;
    Output = output;
    Max = max;
    Discovered = new String[max+1];
    Discovered[0] = seed;
    PQ.add(seed, priority);
    priority--;
  }

  public static void main(String[] args) throws Exception
  {
    String[] topics = new String[1];
    WikiCrawler wc = new WikiCrawler("/wiki/Main_Page", 20, topics, "WikiCCResults");
    wc.crawl(false);
  }

  /*
  Should be pretty much done, untested
  needs to account for scanning just one entire line instead of a properly spaced HTML


  *Possible bug, web page where there are links the same page multiple times, what happens with this?
  *How to check that the same edge does not appear twice, but different root can point to link already in Discovered?
  */
  public ArrayList<String> extractLinks(String document, String relAddress) throws Exception
  {
    //Scan for links, when a new link is found, the contents of that link are scanned for keywords
    //if the new link has all the keywords in topics, the link gets added to the arraylist
    ArrayList<String> links = new ArrayList<String>();
    Scanner scan = new Scanner(document);
    scan.useDelimiter("");
    scan.findInLine("<p>"); //this will move the cursor to the first <p> tag
    while (scan.hasNext())
    {
      if (scan.findInLine("<a href=\"/wiki/") == null) break;
      String tempLink = new String("/wiki/");
      int stop = 1;
      boolean dontadd = false;
      //check for non-approved characters and extracts link
      while (stop == 1)
      {
        String s = scan.next();
        if (s.charAt(0) == '#' || s.charAt(0) ==':')
        {
          //dont add this link to arraylist
          //Code is still trying to open invalid links
          dontadd = true;
          break;
        }
        else if (s.charAt(0) == '"')
        {
          //stop, link has been extracted
          stop = 0;
        }
        else
        {
          tempLink+=s;
        }
      }
      String html = new String();
      if (dontadd == false) html = URLtoString(BASE_URL+tempLink); //only read this is dontadd is false
      if (!dontadd && checkTopics(html))
      {
        boolean dothething = true;
        boolean dotheotherthing = true;
        for (int k = 0; k < links.size(); k++)
        {
        	if (tempLink.compareTo(links.get(k)) == 0 || tempLink.compareTo(relAddress) == 0) dotheotherthing = false;

        }
        if (dotheotherthing) links.add(tempLink);
        for (int i = 0; i < Discovered.length; i++)
        {
          if (Discovered[i] == tempLink)
          {
            dothething = false;
          }
        }
        if (dothething && numLinks < Discovered.length)
        {
          Discovered[numLinks] = tempLink;
          numLinks++;
        }
        if (numLinks == Discovered.length)
        {
        	break;
        }
      }
    }
    scan.close();
    return links;
  }

  public void crawl(boolean focused) throws Exception
  {
  //start at seed and extract the links. For all the links discovered up to max number of links
  //write that edge to the file.
  String currLink = Seed; //current link being analyzed
  ArrayList<String> links = new ArrayList<String>();
  if (focused)
  {
    boolean allstop = false;
	  for (int i = 0; i < numLinks; i++)
	  {
		  String html = URLtoString(BASE_URL+Discovered[i]);
		  links = extractLinks(html, Discovered[i]);
		  for (int k = 0; k < links.size(); k++)
		  {
			  if (checkTopics(links.get(k)))
			  {
				  if (numLinks == Discovered.length) allstop = true;
				  else
				  {
					  Discovered[numLinks] = links.get(k);
					  numLinks++;
				  }
			  }
			  if (writeContent == null) writeContent = Discovered[i] + " ";
			  else writeContent += Discovered[i] + " ";
			  writeContent += links.get(k) + " ";
		  }
		  if (allstop) break;
	  }
  }
  else
  {
	  boolean allstop = false;
	  for (int i = 0; i < numLinks; i++)
	  {
		  String html = URLtoString(BASE_URL+Discovered[i]);
		  links = extractLinks(html, Discovered[i]);
		  for (int k = 0; k < links.size(); k++)
		  {
			  if (checkTopics(links.get(k)))
			  {
				  if (numLinks == Discovered.length) allstop = true;
				  else
				  {
					  Discovered[numLinks] = links.get(k);
					  numLinks++;
				  }
			  }
			  if (writeContent == null) writeContent = Discovered[i] + " ";
			  else writeContent += Discovered[i] + " ";
			  writeContent += links.get(k) + " ";
		  }
		  if (allstop) break;
	  }
  }
  WriteToFile();
  }

  //Writes the content at URL (page) to a string
  //page is a relative wiki address
  private String URLtoString(String page) throws Exception
  {
    String HTML = "";
    URL url;
    InputStream is;
    //sleeps if there have been 20 requests
    if (req==20)
    {
    	req = 0;
    	Thread.sleep(3000);
    }
    url = new URL(page);
    is = url.openStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    req++;
    String temp;
    temp = br.readLine();
    while (temp != null)
    {
      HTML+=temp;
      temp = br.readLine();
    }
    return HTML;
  }

  /*
  Doesn't work just yet, probably something to do with the if statement

  returns true if all the topics are in the document, false otherwise
  */
  private boolean checkTopics(String document)
  {
	String lower = document.toLowerCase();
    Scanner scan = new Scanner(lower);
    scan.useDelimiter("");
    scan.findInLine("<p>");
    for (int i  = 0; i < Topics.length; i++)
    {
      if (Topics[i] == null || scan.findInLine(Topics[i]) == null)
      {
    	  scan.close();
        return false;
      }
      scan = new Scanner(lower);
    }
    scan.close();
    return true;
  }

  //Needs testing
  private void WriteToFile() throws Exception
  {
    //not sure if I need to pass in arguments for this one
    BufferedWriter bw = new BufferedWriter(new FileWriter(Output));
    if (writeContent == null); 
    else
    {
    	Scanner scan = new Scanner(writeContent);
    	while (scan.hasNext())
    	{
    		bw.write(scan.next());
    		bw.write(" "); //not sure if this is needed, check how this function writes.
    		bw.write(scan.next());
    		bw.newLine();
    	}
    	scan.close();
    }
    bw.close();
  }
}