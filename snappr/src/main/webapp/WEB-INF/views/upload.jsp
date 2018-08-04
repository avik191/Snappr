   
    <div class="upload-button-left-nav">
        <img src="${images }/post-icon.png" alt="Post image">
    </div>

    <div class="upload-popup-container">
        <div class="upload-overlay"></div>
        <div class="upload-popup">
            <div class="upload-popup-left-section">

                <div class="upload-file-input-wrapper">
                    <input type="file" name="file" id="select-file" accept="image/*">
                    <label for="select-file">Choose a file...</label>
                </div>
                <div class="upload-description-input-wrapper">
                    <textarea name="description" id="upload-description" placeholder="Type something here..."></textarea>
                </div>

                <div class="upload-location-input-wrapper">
                    <input type="text" name="location" id="upload-location" placeholder="Where was this photo taken?">
                </div>

                <div class="upload-button-wrapper">
                    <button class="upload-button">Upload</button>
                </div>
            </div>

            <div class="upload-popup-right-section">
                <div class="upload-preview-heading">
                    <h3>Preview</h3>
                </div>
                <div class="upload-preview-card">
                    <div class="upload-preview-image">
                        <img src="${images }/image_placeholder.png" alt="Image preview">
                    </div>
                    <div class="upload-preview-details-container">
                        <div class="upload-preview-description">
                                Image description will go here. You can use upto 160 words.
                        </div>
                        <div class="upload-preview-location">
                            Neverland
                        </div>
                    </div>
                </div>
            </div>

            <div class="upload-status-container">
                <div class="upload-status-percent">Uploaded 0%...</div>
                <div class="upload-status-bar"></div>
            </div>
        </div>
    </div>
    
        <script src="${js }/upload.js"></script>
