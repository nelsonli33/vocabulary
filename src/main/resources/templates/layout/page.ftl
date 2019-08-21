<#macro page>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>${pageTitle} | 單字卡訓練應用</title>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1" name="viewport">
        <meta name="_csrf" content="${_csrf.token}"/>
        <meta name="_csrf_header" content="${_csrf.headerName}"/>

        <!-- Font Awesome -->
        <link crossorigin="anonymous" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
              integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" rel="stylesheet">

        <link rel="stylesheet" type="text/css" href="../../static/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../../static/css/style.css"/>
    </head>
    <body>

    <#include "../common/navbar.ftl">

    <main>
        <#nested>
    </main>

    <#include "../common/footer.ftl">

    <!-- jQuery library -->
    <script src="../../static/js/jquery-3.4.1.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <script src="../../static/js/bootstrap.min.js"></script>
    <script src="../../static/js/main.js"></script>
    </body>
    </html>
</#macro>
