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

           <form action="${pageContext.request.contextPath}/ServletControlador?accion=modificar&idCliente=${cliente.id_cliente}"
              method="POST" class="was-validated">
            <!--Botones navegacion-->
            <jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicion.jsp" />

            <Section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Editar Cliente</h4>
                                </div>
                                <div class="card-body">

                                    <div class="form-group">
                                        <label for="nombre">Nombre</label>
                                        <input type="text" class="form-control" name="nombre" required="" value="${cliente.nombre}">
                                    </div>
                                    <div class="form-group">
                                        <label for="apellido">Apellido</label>
                                        <input type="text" class="form-control" name="apellido" required="" value="${cliente.apellido}">
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="email" class="form-control" name="email" required="" value="${cliente.mail}">
                                    </div>
                                    <div class="form-group">
                                        <label for="telefono">Telefono</label>
                                        <input type="tel" class="form-control" name="telefono" required="" value="${cliente.tlf}">
                                    </div>
                                    <div class="form-group">
                                        <label for="saldo">Saldo</label>
                                        <input type="number" class="form-control" name="saldo" required="" value="${cliente.saldo}">
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </Section>
        </form>

        <!--Pie de pagina-->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

        <!--Scripts de FontAwesome-->
        <jsp:include page="/WEB-INF/paginas/comunes/scriptFA.jsp" />
    </body>
</html>
