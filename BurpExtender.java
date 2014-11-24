package burp;

import java.io.BufferedWriter;
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
    
    public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo)
    {
    	Date date = new Date();
    	SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHH.");
    	String dateString = simpledateformat.format(date);
    	String autolog = dateString + "log";
    	String request = "";
    	String response = "";
    	SimpleDateFormat simpledateformat1 = new SimpleDateFormat("HH:mm:ss");
    	String dateOutput = simpledateformat1.format(date);
    	
    	try
    	{
    		if(messageInfo.getResponse() != null)
    		{
    		
    			FileWriter filewriter = new FileWriter(autolog, true);
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