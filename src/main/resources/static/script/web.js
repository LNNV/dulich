var images = document.querySelectorAll('.wrapper_item img')
var prev = document.querySelector('.gallery_prev')
var next = document.querySelector('.gallery_next')
var close = document.querySelector('.gallery_close')
var gallery = document.querySelector('.gallery')
var galleryImg = document.querySelector('.gallery_img img')
var wrapper = document.querySelector('.wrapper')

var currentIndex =0;
function showGallery() {
    if(currentIndex==0) {
        prev.style.display= "none";
    } else {
        prev.style.display= "block";
    }
    if(currentIndex==5) {
        next.style.display ="none";
    } else {
        next.style.display ="block";
    }
    //
    galleryImg.src =images[currentIndex].src;
    gallery.style.opacity = 1;
    gallery.style.transform =  "scale(1)";
    gallery.style.pointerEvents  = "auto"; 
}
images.forEach((item,index)=> {
    item.addEventListener('click',function() {
        currentIndex=index;
        showGallery()
    })
})

close.addEventListener("click", function(e) {    
    gallery.style.opacity = 0;
    gallery.style.transform =  "scale(0.5)";
    gallery.style.pointerEvents  = "none";   
})
// close.addEventListener('keyDown', function(e) {
//     console.log(e.keyCode)
//     if(e.keyCode == 27) {
//         gallery.style.opacity = 0;
//         gallery.style.transform =  "scale(0.5)";
//         gallery.style.pointerEvents  = "none";   
//     }
// })

prev.addEventListener("click", function(){
    if(currentIndex >0) {
        console.log(2);
        currentIndex--;
        showGallery()
    } else {
        showGallery()
    }

})
next.addEventListener("click", function(){
    if(currentIndex <5) {
        currentIndex++;
        showGallery()
    } else {
        showGallery()
    }

})
