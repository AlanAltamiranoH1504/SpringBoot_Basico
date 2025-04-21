document.addEventListener("DOMContentLoaded", () => {
    peticionListarCategorias();

    const btnNewCategoria = document.querySelector("#newCategoria");

    //Eventos del DOM
    btnNewCategoria.addEventListener("click", sendFormNuevaCategoria);

    //Funciones
    function peticionListarCategorias() {
        fetch("/categorias/findAll", {
            method: "GET"
        }).then((respose) => {
            return respose.json();
        }).then((data) => {
            listadoCategorias(data)
        }).catch((error) => {
            console.log("Error en peticion a listado de categorias");
            console.log(e.message);
        })
    }

    function listadoCategorias(data) {
        const {categorias} = data;
        const tbodyCategorias = document.querySelector("#tbody-categorias");
        categorias.forEach((categoria) => {
            const trCategoria = document.createElement("tr");
            trCategoria.innerHTML = `
                <td>${categoria.id}</td>
                <td>${categoria.nombre}</td>
                <td class="d-flex justify-content-around">
                    <button type="button" id="btnEditar-${categoria.id}" data-id="${categoria.id}" class="btn btn-warning d-flex justify-content-center align-items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-edit"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg>
                    </button>
                    <button type="button" id="btnEliminar-${categoria.id}" data-id="${categoria.id}" class="btn btn-danger d-flex justify-content-center align-items-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x-circle"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg>
                    </button>
                </td>
            `;
            tbodyCategorias.appendChild(trCategoria);
        })
    }

    function sendFormNuevaCategoria(e) {
        e.preventDefault();
        console.log("Haciendo click en el boton de nueva categria");
    }
})