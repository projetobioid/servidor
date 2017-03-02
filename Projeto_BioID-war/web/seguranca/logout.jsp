<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html lang="pt-br">
  <head>
    <meta charset="utf-8">
<!--    <meta http-equiv="X-UA-Compatible" content="IE=edge">-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="Daniel Defante">
    <!--link rel="icon" href="../../favicon.ico"-->

    
    
    <link rel="stylesheet" href="../css/font-awesome.min.css">

    
    
    <title>BioID</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

  </head>

  <body >


        <div class="container" >



            <div class="row" style="margin-top: 100px;">

                  <div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-lg-4 col-lg-offset-4 text-center">

                  <div class="panel panel-default">
                      <div class="panel-body">

                          <span class="fa fa-sign-out amarelo" aria-hidden="true" style="font-size: 100px;">
                              
                          </span>
                          <hr>
                          <h1>Logout realizado com sucesso!</h1>

                      </div>

                  </div>

              </div>

          </div>


        </div> <!-- /container -->
        <div class="panel-footer navbar-fixed-bottom text-center"><a href="../" style="color: #fff;"><span class="fa fa-reply" aria-hidden="true"></span>&nbsp;Início</a></div>

 
    
  </body>
  
    <%
        request.getSession().invalidate();
    %>
</html>