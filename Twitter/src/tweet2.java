
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;


public class tweet2
{
    public static void main(String[] args) throws TwitterException, IOException, InterruptedException 
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
         .setOAuthConsumerKey("p8AMdaW3nNeshoPJkk2uf1heL")
         .setOAuthConsumerSecret("M55v1vIPA3giY73LEIWC5Zf1X087OU4d8YjiC1asJKCBSJq53H")
         .setOAuthAccessToken("3959219589-6BRc4Ln0k5ZEq03t1aeZcChZRXPLv1BXpsvmTQ2")
         .setOAuthAccessTokenSecret("Mj7LZXfRTJKZjDwVqQ9qkagrw6TYTREUfUjzzmfRpBX3u");
        TwitterFactory tf = new TwitterFactory(cb.build());
        
        Twitter twitter = tf.getInstance();
        Query query = new Query("Star Wars");
        QueryResult result;
        
        String json="";
                
        int x = 0;
        int y = 1;
        try 
        {
          while (true)
          {
             result = twitter.search(query);
             for (Status tweet : result.getTweets()) 
             {
               if(x > 3000) 
               {
                   y++;
                   x=1; 
               }
                json = DataObjectFactory.getRawJSON(tweet);
                System.out.println("Tweet:"+x+"------------->\n"+json+"\n");
                
                File f = new File("c:/prueba"+y+".txt");
                FileWriter w = new FileWriter(f,true);
                BufferedWriter bw = new BufferedWriter(w);
                PrintWriter wr = new PrintWriter(bw);  
                wr.write(json+"\n");//escribimos en el archivo
                wr.close();
                bw.close();
                x++;
             }
             Thread.sleep (30*1000);
          }
        } catch (TwitterException ex) {} 
    }//Main
}//Fin de la clase