package fellesp;

public class CalandarHandler{
   
   private boolean LoggedIn=false;
   private User user;
   
 //  public CalendarHandler(User user){
   //   this.employee=user;
   //}
   
   public void LogIn(String username, String password1){
     String password2 = null;
	   //SPØRRING:password2 =select password from User as e where username=e.username
     if (password1.equals(password2)) {
       LoggedIn = true;
     }
   }
   
   public void LogOut(){
      LoggedIn=false;
   }
   
   
   
}
