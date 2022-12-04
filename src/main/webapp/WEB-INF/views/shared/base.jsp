<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<html lang="en" xmlns:layout="http://kwonnam.pe.kr/jsp/template-inheritance">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

        <!-- Customized Bootstrap Stylesheets -->
        <layout:block name="style">
            base_page_style
        </layout:block>

        <title><layout:block name="title">base_head_title</layout:block></title>
    </head>
    <body>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>

       <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
         <div class="container-fluid">
           <a class="navbar-brand" href="" style="font-weight: bolder; color: cadetblue">Shopify Admin</a>
           <div class="navbar-collapse" id="navbarSupportedContent">
             <ul class="navbar-nav me-auto ">
               <li class="nav-item"><a class="nav-link active" aria-current="page" href="/e-commerce/admin/adminHome" >Home</a></li>

               <li class="nav-right my-2 my-lg-0" style="float: right; padding-left: 150vh !important;">
                   <a class="nav-link active" aria-current="page" href="" >Logout</a></li>
             </ul>
           </div>
         </div>
       </nav>

       <div id="content">
        <layout:block name="content">
            base_body_content
        </layout:block>
       </div>

    </body>
</html>