/* recuperarTexto = function(idComponmente){
    let componente  = document.getElementById(idComponmente)
    let valorComponente = componente.value;
    return valorComponente;
} */

recuperarTexto = (idComponmente) =>{
    let componente  = document.getElementById(idComponmente)
    let valorComponente = componente.value
    return valorComponente
}

recuperarEntero = (idComponmente) =>{
    let valorTexto = recuperarTexto(idComponmente)
    let valorEntero = parseInt(valorTexto)
    
    return valorEntero
}

recuperarFloat = (idComponente) =>{
    let valorTexto = recuperarTexto(idComponente)
    let valorFloat = parseFloat(valorTexto)

    return valorFloat
}
