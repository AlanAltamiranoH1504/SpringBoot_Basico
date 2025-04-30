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
        console.log("Enviado peticion de reporte PDF")
    }
    function peticionReporteExcel() {
        console.log("Enviado peticion de reporte Excel")
    }
    function peticionReporteCSV() {
        console.log("Enviado peticion de reporte CSV")
    }
});