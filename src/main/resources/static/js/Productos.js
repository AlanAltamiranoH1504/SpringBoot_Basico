document.addEventListener("DOMContentLoaded", () => {
    peticionSelectCategorias();
    listBackeEnd();

    const btnFormulario = document.querySelector("#sendForm");
    const seccionAlertas = document.querySelector("#alertas");
    const seccionAlertasFuera = document.querySelector("#alertas_fuera");
    const tbody_productos = document.querySelector("#tbody-productos");
    const seccionAlertasUpdate = document.querySelector("#errores_actualizacion");
    const formArchivos = document.querySelector("#form_archivos");

    //Eventos
    btnFormulario.addEventListener("click", formularioEnviado);
    formArchivos.addEventListener("submit", formularioArchivosEnviado);

    //Funciones
    function peticionSelectCategorias(){
        fetch("/categorias/findAll", {
            method: "GET"
        }).then((response) => {
            return response.json();
        }).then((data) => {
            llenadoSelectCategorias(data);
        }).catch((e) => {
            console.log("Error en peticion para listado de categorias");
            console.log(e.message);
        })
    }

    function llenadoSelectCategorias(categoriasObject){
        const {categorias} = categoriasObject;
        const selectCategorias = document.querySelector("#categorias");
        categorias.forEach((categoria) => {
            const optionCategoria = document.createElement("option");
            optionCategoria.value = categoria.id;
            optionCategoria.text = categoria.nombre;
            selectCategorias.appendChild(optionCategoria);
        });
    }

    function formularioEnviado(e) {
        e.preventDefault();
        seccionAlertas.innerHTML = "";
        let objetoProducto = {};
        const inputNombre = document.querySelector("#nombre").value;
        const inputDescripcion = document.querySelector("#descripcion").value;
        const inputPrecio = document.querySelector("#precio").value;
        const inputSlug = document.querySelector("#slug").value;
        const inputCategoria = document.querySelector("#categorias").value;

        //Validacion
        if (inputNombre.trim() === "" || inputNombre == null) {
            const mensajeAlerta = document.createElement("p");
            mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
            mensajeAlerta.textContent = "Nombre del producto vacio";

            seccionAlertas.classList.add("bg-danger", "fw-bold", "rounded");
            seccionAlertas.appendChild(mensajeAlerta);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
            return;
        }
        if (inputSlug.trim() === "" || inputSlug == null) {
            const mensajeAlerta = document.createElement("p");
            mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
            mensajeAlerta.textContent = "Slug del producto vacio";

            seccionAlertas.classList.add("bg-danger", "fw-bold", "rounded");
            seccionAlertas.appendChild(mensajeAlerta);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
            return;
        }
        if (inputDescripcion.trim() === "" || inputDescripcion == null) {
            const mensajeAlerta = document.createElement("p");
            mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
            mensajeAlerta.textContent = "Descripcion del producto vacia";

            seccionAlertas.classList.add("bg-danger", "fw-bold", "rounded");
            seccionAlertas.appendChild(mensajeAlerta);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
            return;
        }
        if (inputPrecio.trim() === "" || inputDescripcion == null || isNaN(inputPrecio)) {
            const mensajeAlerta = document.createElement("p");
            mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
            mensajeAlerta.textContent = "Formato del precio incorrecto o vacio";

            seccionAlertas.classList.add("bg-danger", "fw-bold", "rounded");
            seccionAlertas.appendChild(mensajeAlerta);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
            return;
        }
        if (inputCategoria.trim() === "" || inputCategoria == null) {
            const mensajeAlerta = document.createElement("p");
            mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
            mensajeAlerta.textContent = "Categoria del producto vacio";

            seccionAlertas.classList.add("bg-danger", "fw-bold", "rounded");
            seccionAlertas.appendChild(mensajeAlerta);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
            return;
        }
        objetoProducto = {
            nombre: inputNombre,
            slug: inputSlug,
            descripcion: inputDescripcion,
            precio: inputPrecio,
            categoriaId: inputCategoria
        };
        saveProductoDB(objetoProducto);
    }

    function saveProductoDB(producto) {
        fetch("/productos/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(producto)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            const {errors} = data;
            if (errors){
                alertasErrores(errors);
            }else{
                mostrarAlertas("Producto guardado correctamente", true);
                limpiarInputs();
                tbody_productos.innerHTML = "";
                listBackeEnd();
            }
        }).catch((e) => {
            console.log("Error en peticion al back");
        });
    }

    function mostrarAlertas(mensaje, tipo, lugar) {
        if (tipo) {
            if (lugar === "fuera"){
                const mensajeAlerta = document.createElement("p");
                mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
                mensajeAlerta.textContent = `${mensaje}`;

                seccionAlertasFuera.classList.add("bg-success", "fw-bold", "rounded");
                seccionAlertasFuera.appendChild(mensajeAlerta);
                setTimeout(() => {
                    seccionAlertasFuera.innerHTML = "";
                }, 3000);
            }else{
                const mensajeAlerta = document.createElement("p");
                mensajeAlerta.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white");
                mensajeAlerta.textContent = `${mensaje}`;

                seccionAlertas.classList.add("bg-success", "fw-bold", "rounded");
                seccionAlertas.appendChild(mensajeAlerta);
                setTimeout(() => {
                    seccionAlertas.innerHTML = "";
                }, 3000);
            }
        } else {
            console.log("Alerta mala");
        }
    }

    function alertasErrores(errores) {
        const mensajesError = Object.values(errores);
        const seccionAlertas = document.querySelector("#alertas");
        mensajesError.forEach((error) => {
            const parrafoError = document.createElement("p");
            parrafoError.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white", "bg-danger", "mb-3", "rounded", "fw-bold");
            parrafoError.textContent = error;
            seccionAlertas.appendChild(parrafoError);
            setTimeout(() => {
                seccionAlertas.innerHTML = "";
            }, 3000);
        });
    }

    function alertasActualizacion(errores){
        seccionAlertasUpdate.innerHTML = "";
        const {errors} = errores;
        const mensajesErrores = Object.values(errors);

        mensajesErrores.forEach((error) => {
            const parrafoError = document.createElement("p");
            parrafoError.classList.add("text-center", "text-uppercase", "px-3", "py-2", "text-white", "bg-danger", "mb-3", "rounded", "fw-bold");
            parrafoError.textContent = error;
            seccionAlertasUpdate.appendChild(parrafoError);
            setTimeout(() => {
                seccionAlertasUpdate.innerHTML = "";
            }, 3000);
        });
    }

    function limpiarInputs() {
        document.querySelector("#nombre").value = "";
        document.querySelector("#descripcion").value = "";
        document.querySelector("#precio").value = "";
        document.querySelector("#slug").value = "";
        document.querySelector("#categorias").value = "";
    }

    function listBackeEnd() {
        fetch("/productos/list", {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        }).then((response) => {
            return response.json();
        }).then((data) => {
            const {productos} = data;
            listarProductosRenderizado(productos);
        }).catch((error) => {
            console.log("Error en la peticion");
            console.log(e.message);
        })
    }

    function listarProductosRenderizado(productos) {
        productos.forEach((producto) => {
            const tdFoto = producto.foto !== null ? producto.foto : "----";
            const tr_producto = document.createElement("tr");
            tr_producto.innerHTML = `
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.slug}</td>
                <td>${producto.descripcion}</td>
                <td>$ ${producto.precio}</td>
                <td>${tdFoto}</td>
                <td>${producto.categoria.nombre}</td>
                <td>
                    <div class="d-flex justify-content-center align-items-center gap-1">
                        <button type="button" id="editar" data-id-editar="${producto.id}" class="btn btn-warning d-flex justify-content-center align-items-center"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-edit"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg></button>
                        <button type="button" id="eliminar" data-id-eliminar="${producto.id}" class="btn btn-danger d-flex justify-content-center align-items-center"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x-circle"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg></button>
                    </div>
                </td>
            `;
            tbody_productos.appendChild(tr_producto);
        });
    }
    document.querySelector("#tbody-productos").addEventListener("click", (e) => {
        seleccionBtn(e.target);
    });

    function seleccionBtn(id){
        const accion = id.getAttribute("id");
        if (accion === "editar"){
            editarProductoPeticion(id);
        }else{
            eliminarProductoPeticion(id);
        }
    }

    function eliminarProductoPeticion(id) {
        if (!id){
            return;
        }
        const idProducto = id.getAttribute("data-id-eliminar");
        const producto = {
            id: idProducto
        }
        fetch("/productos/delete", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(producto)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            tbody_productos.innerHTML = "";
            mostrarAlertas("Producto eliminado", true, "fuera");
            listBackeEnd();
        }).catch((error) => {
            console.log("Error en la peticion al backend");
            console.log(e.message);
        })
    }
    function editarProductoPeticion(id){
        if (!id){
            return;
        }
        const idProducto = id.getAttribute("data-id-editar");
        const producto = {
            id: idProducto
        }
        fetch("/productos/findById", {
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify(producto)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            llenarFormularioEdicion(data);
        }).catch((error) => {
            console.log("Error en la peticion");
            console.log(error.message);
        });
    }

    function llenarFormularioEdicion(data) {
        const modalActualizacion = new bootstrap.Modal(document.querySelector("#actualizarProducto"));
        modalActualizacion.show();
        document.querySelector("#nombreActualizado").value = data.producto.nombre;
        document.querySelector("#descripcionActualizada").value = data.producto.descripcion;
        document.querySelector("#precioActualizado").value = data.producto.precio;

        const btnActualizar = document.querySelector("#sendFormUpdate");
        btnActualizar.addEventListener("click", () => {
            actualizacionDB(data);
        });
    }

    function actualizacionDB(data){
        const nombreUpdate = document.querySelector("#nombreActualizado").value;
        const descripcionUpdate = document.querySelector("#descripcionActualizada").value;
        const precioUpdate = document.querySelector("#precioActualizado").value;

        if (nombreUpdate.trim() === "" || nombreUpdate == null){
            return;
        }
        if (descripcionUpdate.trim() === "" || descripcionUpdate == null){
            return;
        }
        if (precioUpdate.trim() === "" || precioUpdate == null){
            return;
        }

        const productoActualizar = {
            id: data.producto.id,
            nombre: nombreUpdate,
            descripcion: descripcionUpdate,
            precio: precioUpdate
        }
        fetch("/productos/update", {
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify(productoActualizar)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            if(data.errors) {
                alertasActualizacion(data);
                return;
            }
            nombreUpdate.textContent = "";
            descripcionUpdate.textContent = "";
            precioUpdate.textContent = "";
            document.querySelector("#tbody-productos").innerHTML = "";
            listBackeEnd();
            mostrarAlertas("Producto actualizado", true, "fuera");
        }).catch((error) => {
            console.log("Error en la peticion");
            console.log(error.message);
        });
    }

    function formularioArchivosEnviado(e){
        e.preventDefault();
        const archivo = document.querySelector("#archivos").files[0];
        const formData = new FormData();
        formData.append("archivo", archivo);

        fetch("/productos/upload-archivos", {
            method: "POST",
            body: formData
        }).then((response) => {
            return response.json();
        }).then((data) => {
            const {status} = data;
            alert(status);
        }).catch((e) => {
            console.log("Errror en la peticion");
            console.log(e.message);
        })
    }
});