

function initUploadPhotosButton(){
    var descFlag = 0, locFlag = 0;

    var $elements = {
        $uploadButtonLeftNav: $(".upload-button-left-nav"),
        $uploadOverlay: $(".upload-overlay"),
        $uploadPopup: $(".upload-popup-container"),
        $uploadButton: $(".upload-button"),
        $uploadStatusContainer: $(".upload-status-container"),
        $uploadStatusText: $(".upload-status-percent"),
        $uploadStatusBar: $(".upload-status-bar"),
        $inputElements: {
            $fileInput: $("#select-file"),
            $fileInputLabel: $("#select-file + label"),
            $descriptionInput: $("#upload-description"),
            $locationInput: $("#upload-location")
        },
        $previewElements: {
            $previewImg: $(".upload-preview-image > img"),
            $previewDescription: $(".upload-preview-description"),
            $previewLocation: $(".upload-preview-location")
        }
    };

    //show popup when upload button is clicked
    $elements.$uploadButtonLeftNav.click(function(e){
        $elements.$uploadPopup.fadeIn(200);
    });

    $elements.$uploadOverlay.click(function(e){
        $elements.$uploadPopup.fadeOut(200);
    })

    $elements.$inputElements.$fileInput.change(function(e){
        if (this.files && this.files[0]) {
            var reader = new FileReader();
        
            reader.onload = function(e) {
                var image = new Image();
 
                //Set the Base64 string return from FileReader as source.
                image.src = e.target.result;
                image.onload = function(ev){
                    var width = this.width;
                    var height = this.height;
                    displayImage(width, height, e);
                }
            }
        
            reader.readAsDataURL(this.files[0]);
          }
    });

    function displayImage(w, h, ev){
        var expectedRatio = 1;
        var actualRatio = w/h;
        var calculatedWidth, calculatedHeight;
        if(actualRatio <= expectedRatio){
            calculatedWidth = 240;
            calculatedHeight = Math.round(240 / actualRatio);
        }
        else{
            calculatedHeight = 240;
            calculatedWidth = Math.round(240 * actualRatio);
        }

        $elements.$previewElements.$previewImg.css("height", calculatedHeight + "px");
        $elements.$previewElements.$previewImg.css("width", calculatedWidth + "px");
        $elements.$previewElements.$previewImg.attr("src", ev.target.result);
    }

    $elements.$inputElements.$descriptionInput.keyup(function(e){
        if(descFlag == 0){
            descFlag = 1;
            $elements.$previewElements.$previewDescription.html("");
        }

        if(e.key == "Enter"){
            submitForm();
            return;
        }

        $elements.$previewElements.$previewDescription.html(this.value);
    });

    $elements.$inputElements.$locationInput.keyup(function(e){
        if(locFlag == 0){
            locFlag = 1;
            $elements.$previewElements.$previewLocation.html("");
        }

        if(e.key == "Enter"){
            submitForm();
            return;
        }

        $elements.$previewElements.$previewLocation.text(this.value);
    });

    $elements.$uploadButton.click(function(e){
        submitForm();
    });

    function submitForm(){

        //show status container
        $elements.$uploadStatusContainer.fadeIn();
        $elements.$uploadButton.addClass("upload-button-disabled");

        var imgFile = $elements.$inputElements.$fileInput.get(0).files[0];
        var description = $elements.$inputElements.$descriptionInput.val();
        var location = $elements.$inputElements.$locationInput.val();

        var formdata = new FormData();
        formdata.append("file", imgFile);
        formdata.append("desc", description);
        formdata.append("location", location);

        var ajax = new XMLHttpRequest();
        ajax.upload.addEventListener("progress", updateProgressStatus, false);
        ajax.addEventListener("load", completeHandler, false);
        ajax.addEventListener("error", errorHandler, false);
        ajax.addEventListener("abort", abortHandler, false);
        ajax.open("POST", "handlePicUpload");
        ajax.send(formdata);

    }

    function completeHandler(){
        resetForm();
        resetPreviewElements();
        //alert("Successfully uploaded!");
        window.location.href = '/snappr/homePage';
        resetUploadStatus();
    }

    function errorHandler(){
        alert("Oops! There was an error uploading the file. Please try again.");
        resetUploadStatus();
        $elements.$uploadButton.removeClass("upload-button-disabled");
    }

    function abortHandler(){
        alert("Oops! There was an error uploading the file. Please try again.");
        resetUploadStatus();
        $elements.$uploadButton.removeClass("upload-button-disabled");
    }

    function resetForm(){
        descFlag = 0, locFlag = 0;
        $elements.$inputElements.$fileInput.val("");
        $elements.$inputElements.$descriptionInput.val("");
        $elements.$inputElements.$locationInput.val("");
        $elements.$uploadButton.removeClass("upload-button-disabled");
    }

    function resetPreviewElements(){
        $elements.$previewElements.$previewImg.attr("src", window.contextRoot+"/resources/images/image_placeholder.png");
        $elements.$previewElements.$previewImg.css("height", "240px");
        $elements.$previewElements.$previewImg.css("width", "240px");
        $elements.$previewElements.$previewDescription.html("Image description will go here. You can use upto 160 words.");
        $elements.$previewElements.$previewLocation.html("Neverland");
    }

    function resetUploadStatus(){
        $elements.$uploadStatusContainer.fadeOut(0);
        $elements.$uploadStatusText.html("Uploaded 0%...");
        $elements.$uploadStatusBar.css("width", "0px");
    }

    function updateProgressStatus(ev){
        var sent = ev.loaded, total = ev.total;
        var percent = Math.round((sent/total) * 100);
        $elements.$uploadStatusText.html("Uploaded " + percent + "%...");
        $elements.$uploadStatusBar.css("width", percent + "%");
    }
}

initUploadPhotosButton();