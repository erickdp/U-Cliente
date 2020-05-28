<!DOCTYPE html>
<html>
    <head>
        <!--Etiquetas meta-->
        <jsp:include page="/WEB-INF/paginas/comunes/etiquetasMeta.jsp" />
        <title>Control de clientes</title>
    </head>
    <body>
        <!--Cabezero-->
        <jsp:include page="/WEB-INF/paginas/comunes/cabezero.jsp" />

        <!--Seccion agregar Cliente-->
        <jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacion.jsp" />

        <!--Listade de clientes-->
        <jsp:include page="/WEB-INF/paginas/cliente/listadoClientes.jsp" />

        <!--Pie de pagina-->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />
        
        <!--Scripts de FontAwesome-->
        <jsp:include page="/WEB-INF/paginas/comunes/scriptFA.jsp" />
    </body>
</html>
