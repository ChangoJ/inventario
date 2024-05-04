recuperar = () => {
    let frutas = ["pera", "manzana", "piña", "sandia"]
    frutas.push("banana")
    return frutas
}

testRecuperar = () => {
    let misFrutas = recuperar();
    let frutaPrimera = misFrutas[0]
    let otraFruta = misFrutas[1]

    console.log("1>>>" + frutaPrimera)
    console.log("2>>>" + otraFruta)
}

testRecuperarDes = () =>{
    let [frutaPrimera,frutaSegunda,frutaTercera] = recuperar();
    console.log(frutaPrimera,frutaSegunda,frutaTercera)
}