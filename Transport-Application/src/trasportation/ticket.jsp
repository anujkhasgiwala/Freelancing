<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="ISO-8859-1" session="true"%>
<%
 Connection c= null;
try{
	Class.forName("com.mysql.jdbc.Driver");
       c=DriverManager.getConnection("jdbc:mysql://localhost:3306/transport","root","root");

}
catch(Exception e)
{
    System.out.println("Exception:"+e);
};
Statement stmt = c.createStatement();
String username =(String) session.getAttribute("sno");
ResultSet rst = stmt.executeQuery("select * from profile where username='"+username+"'");
rst.next();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<title>The Diary</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<div id="wrap">

       <div class="header">
       		<div class="logo"><a href="#">
				<img src="images/1d.png" alt="" title="" border="0" height="40" width="100" /></a></div>            
        <div id="menu">
            <ul>                                                                       
            <li><a href="home.html">Home</a></li>
            <li><a href="about.html">About Us</a></li>
            <li><a href="specials.html">Friends</a></li>
            <li class="selected"><a href="profile.jsp">My Profile</a></li>
            <li><a href="contact.html">Contact</a><a href="category.html">Sign 
			Out</a></li>
            </ul>
        </div>     
            
            
            
       </div> 
       
       
       <div class="center_content">
       	<div class="left_content">
            <div class="title"><span class="title_icon"><img src="images/bullet1.gif" alt="" title="" /></span>Edit 
				Profile </div>
        
        	<div class="feat_prod_box_details">
            <p class="details">
             Tell everybody something about you....
            </p>
            
              	<div class="contact_form">
                <div class="form_subtitle">My Profile</div>
                 <form name="register" action="#">          
                     <table width="400" border="0">
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">Name</span></div></td>
        <td width="235"><span class="style28"><%=rst.getString("name")%></span></td>
      </tr>
           
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">Date Of Birth</span></div></td>
        <td><label></label><span class="style28"><%=rst.getString("dob")%></span></td>
      </tr>
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">Address</span></div></td>
        <td><label>
          
       <span class="style28"> <%=rst.getString("add")%></span></label></td>
      </tr>
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">City</span></div></td>
        <td><label><span class="style28"><%=rst.getString("city")%></span></label></td>
      </tr>
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">State</span></div></td>
        <td><label><span class="style28"><%=rst.getString("stat")%></span></label></td>
      </tr>
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">Country</span></div></td>
        <td><label><span class="style28"><%=rst.getString("country")%></span></label></td>
      </tr>
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">Mobile</span></div></td>
        <td><label><span class="style28"><%=rst.getString("mobile")%></span></label></td>
      </tr>
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">Sex</span></div></td>
        <td><label><span class="style28"><%=rst.getString("sex")%></span></label></td>
      </tr>
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">I am at</span></div></td>
        <td><label><span class="style28"><%=rst.getString("at")%></span></label></td>
      </tr>
      <tr>
        <td height="40" style="width: 156px"><div align="left"><span class="style28">Hobbies</span></div></td>
        <td><label><span class="style28"><%=rst.getString("hobbies")%></span></label></td>
      </tr>
       
    </table>                     
                     
                      
                                


                    

                    
                    <div class="form_row">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="edit_profile.html">
		    <img src="images/su.png" alt="" title="" border="0" height="30" width="60" /></a></div>   
                  </form>     
                </div>  
            
          </div>	
            
              

            

            
        <div class="clear"></div>
        </div><!--end of left content-->
        
        <div class="right_content">
        
                
              <div class="cart" style="height: 0px">
                  &nbsp;</div>
        
             <div class="title"><span class="title_icon"><img src="images/bullet3.gif" alt="" title="" /></span>About 
				 The Diray</div> 
             <div class="about">
             <p>
             <img src="images/diary.png" alt="" title="" class="right" height="97" width="150" />
             The Diary is a social utility that brings together all the young minds living across the globe. It is for every individual who is a student or a non-student, fresh graduate,a working professional or an Entrepreneur, and is focused on providing comprehensive solutions for any personal and professional issues.</p>
             
             </div>
             
             <div class="right_box">
             
             	<div class="title"><span class="title_icon"><img src="images/bullet4.gif" alt="" title="" /></span>Promotions</div> 
                    <div class="new_prod_box">
                        <a href="details.html">product name</a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="details.html"><img src="images/thumb1.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    
                    <div class="new_prod_box">
                        <a href="details.html">product name</a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="details.html"><img src="images/thumb2.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>                    
                    
                    <div class="new_prod_box">
                        <a href="details.html">product name</a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="images/promo_icon.gif" alt="" title="" /></span>
                        <a href="details.html"><img src="images/thumb3.gif" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>              
             
             </div>
             
             <div class="right_box">
             
             	<div class="title"><span class="title_icon"><img src="images/bullet5.gif" alt="" title="" /></span>Categories</div> 
                
                <ul class="list">
                <li><a href="#">accesories</a></li>
                <li><a href="#">books gifts</a></li>
                <li><a href="#">specials</a></li>
                <li><a href="#">hollidays gifts</a></li>
                <li><a href="#">accesories</a></li>
                <li><a href="#">books gifts</a></li>
                <li><a href="#">specials</a></li>
                <li><a href="#">hollidays gifts</a></li>
                <li><a href="#">accesories</a></li>
                <li><a href="#">books gifts</a></li>
                <li><a href="#">specials</a></li>                                              
                </ul>
                
             	<div class="title"><span class="title_icon"><img src="images/bullet6.gif" alt="" title="" /></span>Partners</div> 
                
                <ul class="list">
                <li><a href="#">accesories</a></li>
                <li><a href="#">books gifts</a></li>
                <li><a href="#">specials</a></li>
                <li><a href="#">hollidays gifts</a></li>
                <li><a href="#">accesories</a></li>
                <li><a href="#">books gifts</a></li>
                <li><a href="#">specials</a></li>
                <li><a href="#">hollidays gifts</a></li>
                <li><a href="#">accesories</a></li>                              
                </ul>      
             
             </div>         
             
        
        </div><!--end of right content-->
        
        
       
       
       <div class="clear"></div>
       </div><!--end of center content-->
       
              
       <div class="footer">
       	<div class="left_footer"><img src="images/di.png" alt="" title="" /><br /> <a href="http://csscreme.com/freecsstemplates/" title="free templates"><img src="images/csscreme.gif" alt="free templates" title="free templates" border="0" /></a></div>
        <div class="right_footer">
        <a href="#">home</a>
        <a href="#">about us</a>
        <a href="#">services</a>
        <a href="#">privacy policy</a>
        <a href="#">contact us</a>
       
        </div>
        
       
       </div>
    

</div>

</body>
</html>