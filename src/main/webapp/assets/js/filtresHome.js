const E_checks = document.querySelectorAll('*[id^="e-"]')
const V_checks = document.querySelectorAll('*[id^="v-"]')
const radios = document.querySelectorAll('[name="filterConnected"]')
const clickRadio = (V,E)=>{
    V.forEach(input => {
        input.checked=false
        input.disabled=true
    })
    E.forEach(input => {
        input.disabled=false
    })
}

radios.forEach(radio => {
    radio.addEventListener("click",()=>{
        if(radio.getAttribute("id")=="achats"){
            clickRadio(V_checks,E_checks)
        }else
            clickRadio(E_checks,V_checks)
    })
})