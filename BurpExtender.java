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
    	try
    	{
    		FileWriter filewriter = new FileWriter(autolog, true);
    		BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
    		
    		bufferedwriter.write("========================================");
    		bufferedwriter.newLine();
    		bufferedwriter.write(String.valueOf(date));
    		bufferedwriter.newLine();
    		bufferedwriter.write(String.valueOf(messageInfo.getHttpService()));;
    		bufferedwriter.newLine();
    		bufferedwriter.write("========================================");
    		bufferedwriter.newLine();
    		bufferedwriter.newLine();
    		bufferedwriter.write(new String(messageInfo.getRequest()));
    		bufferedwriter.newLine();
    		bufferedwriter.newLine();
    		bufferedwriter.write(new String(messageInfo.getResponse()));
    		bufferedwriter.newLine();
    		bufferedwriter.newLine();
    		bufferedwriter.flush();
    		bufferedwriter.close();
    	}
    	catch(IOException exception)
    	{
//    		System.err.println(exception);
    	}
    }
}