document.addEventListener("DOMContentLoaded", () => {
    //Selectores
    const btnReportePDF = document.querySelector("#btnReportePDF");
    const btnReporteExcel = document.querySelector("#btnReporteExcell");
    const btnReporteCSV = document.querySelector("#btnReporteCSV");

    //Eventos
    btnReportePDF.addEventListener("click", peticionReportePDF);
    btnReporteExcel.addEventListener("click", peticionReporteExcel);
    btnReporteCSV.addEventListener("click", peticionReporteCSV);

    //Funciones
    function peticionReportePDF() {
        fetch("/reportes/pdf", {
            method: "GET",
        }).then((response) => {
            return response.blob();
        }).then((blob) => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement("a");
            a.href = url;
            a.download = "reporte.pdf";
            document.body.appendChild(a);
            a.click();
            a.remove();
            window.URL.revokeObjectURL(url);
        }).catch((error) => {
            console.log(error);
        });
    }
    function peticionReporteExcel() {
        console.log("Enviado peticion de reporte Excel")
    }
    function peticionReporteCSV() {
        console.log("Enviado peticion de reporte CSV")
    }
});