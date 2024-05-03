ejecutarSumar = function(){
    let resultado
    let numero1 = recuperarEntero("txtvalor1");
    let numero2 = recuperarEntero("txtvalor2");

    resultado = sumar(numero1, numero2)
    console.log("La suma es "+resultado)

}

ejecutarResta = () =>{
    let resultado
    let numero1 = recuperarFloat("txtvalor1")
    let numero2 = recuperarFloat("txtvalor2")

    resultado = restar(numero1, numero2)
    console.log("La resta es "+resultado)
}

sumar = (numero1, numero2) =>{
    let resultado 
    resultado = numero1 + numero2;
    return resultado
}

restar = (numero1, numero2)=>{
    let resultado
    resultado = numero1 - numero2
    return resultado
}