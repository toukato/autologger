package burp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BurpExtender implements IBurpExtender, IHttpListener
{
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
    	// register ourselves as an HTTP listener
    	callbacks.registerHttpListener(this);
    }
    
    int number = 1;
    
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo)
    {
    	Date date = new Date();
    	SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
    	String dateString = simpledateformat.format(date);
    	
    	String logFiles = "";
    	logFiles = dateString + "_" + String.format("%02d", number) + ".log";
		File file = new File(logFiles);
		
		if(file.exists())
		{
			if(file.length() >= 104857600)
			{
				number = number + 1;
				logFiles = dateString + "_" + String.format("%02d", number) + ".log";
			}
		}
		
    	String request = "";
    	String response = "";
    	SimpleDateFormat simpledateformat1 = new SimpleDateFormat("HH:mm:ss");
    	String dateOutput = simpledateformat1.format(date);
    	
    	try
    	{
    		if(messageInfo.getResponse() != null)
    		{
    		
    			FileWriter filewriter = new FileWriter(logFiles, true);
    			BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
    			request = new String(messageInfo.getRequest());
    			response = new String(messageInfo.getResponse());
    		
	    		bufferedwriter.write("======================================================");
	    		bufferedwriter.newLine();
	    		bufferedwriter.write(dateOutput);
	    		bufferedwriter.write("  ");
	    		bufferedwriter.write(String.valueOf(messageInfo.getHttpService()));;
	    		bufferedwriter.newLine();
	    		bufferedwriter.write("======================================================");
	    		bufferedwriter.newLine();
	    		bufferedwriter.write(request);
	    		bufferedwriter.newLine();
	    		bufferedwriter.write("======================================================");
	    		bufferedwriter.newLine();
	    		bufferedwriter.write(response);
	    		bufferedwriter.newLine();
	    		bufferedwriter.write("======================================================");
	    		bufferedwriter.newLine();
	    		bufferedwriter.newLine();
	    		bufferedwriter.newLine();
	    		bufferedwriter.newLine();
	    		bufferedwriter.close();
    		}
    	}
    	catch(IOException exception)
    	{
    		System.err.println(exception);
    	}
    }
}