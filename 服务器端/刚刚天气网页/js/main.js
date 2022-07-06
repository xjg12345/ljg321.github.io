jQuery(document).ready(function ($) {


var sidemenuoption = $('.sidemenuoption');
    var menuoption_open = $('.sidemenuoption-open');
    var menuoption_close = $('.menuoption-close');
    var overlay = $('.body-overlay');
    var is_closed = true;
    menuoption_open.on('click', function(e) {
        e.preventDefault();
        if (is_closed == true) {
            is_closed = false;
            sidemenuoption.removeClass('themelazer-close');
            sidemenuoption.addClass('themelazer-open');
            overlay.addClass('overlay-show');
        } else {
            is_closed = true;
            sidemenuoption.removeClass('themelazer-open');
            sidemenuoption.addClass('themelazer-close');
            overlay.removeClass('overlay-show');
        }
    }); 
    menuoption_close.add(overlay).on('click', function(e) {
        e.preventDefault();
        sidemenuoption.removeClass('themelazer-open');
        sidemenuoption.addClass('themelazer-close');
        overlay.removeClass('overlay-show');
        is_closed = true;
    });
    
if (typeof($.fn.slicknav) == 'function') {
        $('.themelazer-navigation').slicknav({
            prependTo: '.themelazer_mobile_menu',
            closedSymbol: '<i class="fa fa-chevron-right"></i>',
            openedSymbol: '<i class="fa fa-chevron-down"></i>',
            label: '',
            allowParentLinks: true
        });
    }


$('.themelazer_search_themelazern').on("click", function() {
      $('.themelazer_search_wrapper').toggleClass('themelazer_search_ative');
      $('.themelazer_search_themelazern').toggleClass('themelazern_search_ative');
      $('.themelazer-navigation').toggleClass('themelazer_navigation_hide');
      
});

$(".themelazer_middle_header").sticky({ topSpacing: 0 });

/* ---------------------------------------------
                    video
----------------------------------------------*/ 
fluidvids.init({
      selector: 'iframe',
      players: ['www.youtube.com', 'player.vimeo.com']
    }); 

//////////////////////////////////////////////////////////////////////////
//        Grid images
//////////////////////////////////////////////////////////////////////////  

$(".justified-gallery-post").justifiedGallery({
    rowHeight: 200,
    captions: false,
    margins : 1,
    lastRow : 'justify',
    fixedHeight : false
  });

//////////////////////////////////////////////////////////////////////////
//        Image
//////////////////////////////////////////////////////////////////////////  


 $('.justified-gallery-post').magnificPopup({
  type:'image', 
  delegate: 'a',
  closeOnContentClick: false,
  closethemelazernInside: false,
  mainClass: 'mfp-with-zoom mfp-img-mobile',
  image: {
            verticalFit: true,
            titleSrc: function(item) {
              return item.el.attr('title');
            }
  },
  gallery: {
      enabled: true,
      navigateByImgClick: true,
      preload: [0,1]
    },
  zoom: {
            enabled: true,
            duration: 300,
            opener: function(element) {
              return element.find('img');
  }}
    

});

//////////////////////////////////////////////////////////////////////////
//        Sidebar
//////////////////////////////////////////////////////////////////////////  

$('.themelazer_sidebar, .themelazer_content').theiaStickySidebar({
      additionalMarginTop: 0
    });

$('.grid').masonry({
  // options
  itemSelector: '.grid-item',
  itemSelector: '.grid-item2',
});
//////////////////////////////////////////////////////////////////////////
//        Slider
//////////////////////////////////////////////////////////////////////////  

var themelazer_slider = $('.themelazer-slider');
      for (i = 0; i < themelazer_slider.length; i++) {
      var $slider_slide = $(themelazer_slider[i]);
  $slider_slide.slick({
        dots: $slider_slide.attr("data-dots") == "true",
        infinite: $slider_slide.attr("data-loop") == "true",
        arrows: $slider_slide.attr("data-arrows") == "true",
        swipe: $slider_slide.attr("data-swipe") == "true",
        autoplay: $slider_slide.attr("data-play") == "true",
    autoplaySpeed: parseInt($slider_slide.attr('data-autospeed')) || 7000,
    pauseOnHover: true,
    adaptiveHeight: true,
    centerMode: true,
    centerPadding: '250px',
    prevArrow:'<div class="jl-arrow-left"><i class="ti-angle-left"></i></div>',
    nextArrow:'<div class="jl-arrow-right"><i class="ti-angle-right"></i></div>',
    slidesToScroll: 1, 
    responsive: [
    {
      breakpoint: 992,
      settings: {
        arrows: false,
        centerMode: true,
        centerPadding: '150px',
      }
    },
    {
      breakpoint: 768,
      settings: {
        arrows: false,
        centerMode: false,

      }
    },
    {
      breakpoint: 480,
      settings: {
        arrows: false,
        centerMode: false,
        
      }
    }
  ]   
  });
    }

var themelazer_slider_center = $('.themelazer-slider-center');
      for (i = 0; i < themelazer_slider_center.length; i++) {
      var $slider_slide = $(themelazer_slider_center[i]);
  $slider_slide.slick({
        dots: $slider_slide.attr("data-dots") == "true",
        infinite: $slider_slide.attr("data-loop") == "true",
        arrows: $slider_slide.attr("data-arrows") == "true",
        swipe: $slider_slide.attr("data-swipe") == "true",
        autoplay: $slider_slide.attr("data-play") == "true",
    autoplaySpeed: parseInt($slider_slide.attr('data-autospeed')) || 7000,
    pauseOnHover: true,
    adaptiveHeight: true,
    prevArrow:'<div class="jl-arrow-left"><i class="ti-angle-left"></i></div>',
    nextArrow:'<div class="jl-arrow-right"><i class="ti-angle-right"></i></div>',
    slidesToScroll: 1,
    responsive: [
    {
      breakpoint: 992,
      settings: {
        arrows: false,
        slidesToShow:1,
      }
    },
    {
      breakpoint: 768,
      settings: {
        arrows: false,
        slidesToShow:1,

      }
    },
    {
      breakpoint: 480,
      settings: {
        arrows: false,
        slidesToShow:1,
        
      }
    }
  ] 
    
  });
    }



var themelazer_slider_mobile = $('.themelazer-slider-mobile');
      for (i = 0; i < themelazer_slider_mobile.length; i++) {
      var $slider_slide = $(themelazer_slider_mobile[i]);
  $slider_slide.slick({
        dots: $slider_slide.attr("data-dots") == "true",
        infinite: $slider_slide.attr("data-loop") == "true",
        arrows: $slider_slide.attr("data-arrows") == "true",
        swipe: $slider_slide.attr("data-swipe") == "true",
        autoplay: $slider_slide.attr("data-play") == "true",
    autoplaySpeed: parseInt($slider_slide.attr('data-autospeed')) || 7000,
    pauseOnHover: true,
    adaptiveHeight: true,
    prevArrow:'<div class="jl-arrow-left"><i class="ti-angle-left"></i></div>',
    nextArrow:'<div class="jl-arrow-right"><i class="ti-angle-right"></i></div>',
    slidesToScroll: 1,    
  });
    }





/* ---------------------------------------------
                    Carousel
----------------------------------------------*/ 
var themelazer_carousel = $('.themelazer-carousel');
      for (i = 0; i < themelazer_carousel.length; i++) {
      var $carousel_col = $(themelazer_carousel[i]);
  $carousel_col.slick({
        dots: $carousel_col.attr("data-dots") == "true",
        infinite: $carousel_col.attr("data-loop") == "true",
        arrows: $carousel_col.attr("data-arrows") == "true",
        swipe: $carousel_col.attr("data-swipe") == "true",
        autoplay: $carousel_col.attr("data-play") == "true",
    autoplaySpeed: parseInt($carousel_col.attr('data-autospeed')) || 7000,
    pauseOnHover: true,
    adaptiveHeight: true,
    prevArrow:'<div class="jl-arrow-left"><i class="ti-angle-left"></i></div>',
    nextArrow:'<div class="jl-arrow-right"><i class="ti-angle-right"></i></div>',
    slidesToScroll: 1,

    responsive: [
          {
            breakpoint: 0,
            settings: {
              slidesToShow: parseInt($carousel_col.attr('data-items')) || 1,
            }
          },
          {
            breakpoint: 479,
            settings: {
              slidesToShow: parseInt($carousel_col.attr('data-xs-items')) || 1,
            }
          },
          {
            breakpoint: 767,
            settings: {
              slidesToShow: parseInt($carousel_col.attr('data-sm-items')) || 1,
            }
          },
          {
            breakpoint: 991,
            settings: {
              slidesToShow: parseInt($carousel_col.attr('data-md-items')) || 1,
            }
          },
          {
            breakpoint: 1199,
            settings: {
              slidesToShow: parseInt($carousel_col.attr('data-lg-items')) || 1,
            }
          },
          {
            breakpoint: 1799,
            settings: {
              slidesToShow: parseInt($carousel_col.attr('data-xl-items')) || 1,
            }
          },
        ]
  });
    }

/* ---------------------------------------------
                    Scroll to Top
----------------------------------------------*/ 
     
        function scrollTopClick() {
      //alert("working");
          if ($(window).scrollTop() > 200) {
            $('.scroll-totop-wrapper').fadeIn();
          } else {
            $('.scroll-totop-wrapper').fadeOut();
          }
        }

        //bottom to top scroll click to go
        $('.scroll-totop-wrapper').on('click', function() {
          $('html, body').animate({scrollTop : 0},1500);
          return false;
        }); 

/* ---------------------------------------------
                    Scroll
----------------------------------------------*/ 
jQuery(window).on('scroll', function(){
    (function ($) {
    scrollTopClick();                
    })(jQuery);
  });

 /*-------------------------------------
    Jquery Serch Box
    -------------------------------------*/
    $('a[href="#header-search"]').on("click", function (event) {
        event.preventDefault();
        var target = $("#header-search"); 
        target.addClass("open");
        setTimeout(function () {
            target.find('input').focus();
        }, 600);
        return false;
    });

    $("#header-search, #header-search button.close ti-close").on("click keyup", function (event) {
        if (
            event.target === this ||
            event.target.className === "close" ||
            event.keyCode === 27
        ) {
            $(this).removeClass("open");
        }
    });


    

  
  });