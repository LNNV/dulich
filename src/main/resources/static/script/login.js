var account=document.querySelector('#fname')
var pass=document.querySelector('#fpass')
var Repass=document.querySelector('#fRepass')
var phoneNumber=document.querySelector('#fphone')
var userName=document.querySelector('#fFullname')
var Email=document.querySelector('#fEmail')
var form=document.querySelector('form')
function showError(input,message){
    let parent=input.parentElement;
    let small=input.parentElement.querySelector('small');
    parent.classList.add('error')
    small.innerText=message;
}

function checkEmail(input){
    const regexEmail =
  /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
  input.value=input.value.trim();
  let isEmailError=!regexEmail.test(input.value)
  if(regexEmail.test(input.value)){
      success(input);
  }else{
      showError(input,"Email không hợp lệ! Vui lòng nhập lại")
  }
  return isEmailError;
}
function success(input){
    let parent=input.parentElement;
    let small=input.parentElement.querySelector('small');
    parent.classList.remove('error')
    small.innerText='';
}
function checkAccount(input){
    input.value=input.value.trim();
    let isAccError=false;
    if(input.value.length<6 || input.value.length>40){
        isAccError=true;
        showError(input,"Lỗi thông tin!Vui lòng nhập từ 6-40 kí tự")
    }else{
        success(input)
    }
    
    return isAccError;
}
function checkPass(inputPass,inputRePass){
    if(inputPass.value!==inputRePass.value){
        showError(inputRePass,"Mật khẩu không trùng khớp")
        return true;
    }
    else{
        success(inputRePass)
    }
}
form.addEventListener('submit',function (e){
    e.preventDefault();
    let isEmail=checkEmail(Email)
    let isAccount=checkAccount(account);
    let isMatchPass=checkPass(pass,Repass);
    if(isEmail||isAccount||isMatchPass){
        //khong lam gi
    }else{
        //thực hiện đăng nhập
    }
})

