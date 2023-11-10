# Poke-Tracker

I ended up just taking the L on the ListView.  
Like you said in class today, I'll accept the -15 for no ListView and +10 for extra credit.  
I also fixed an error that was also present in ContentProviderExample.  
Whenever the database was empty, pressing the previous/next buttons would crash the app.  
This was happening because for some reason mCursor wasn't actually considered null, so it was entering the if statement in the onClick methods for those buttons.  
I just changed the conditional statement from "mCursor != null" to "mCursor.getCount() > 0".  
This solved the problem for me and seems to provide the desired functionality.  
Also, I ended up calling the query method at the bottom of the delete method so that it actually updates the cursor when you delete something.
