var headerDropdown = (function(){
    var parentEl = document.getElementById("header-settings");
    var list = document.getElementById("header-settings-dropdown");

    function hideDropdown(){
        parentEl.setAttribute("data-dropdown-status", "inactive");
        $(list).fadeOut(100);
        parentEl.style.pointerEvents = "initial";
    }

    function showDropdown(){
        parentEl.setAttribute("data-dropdown-status", "active");
        $(list).fadeIn(100).focus();
        parentEl.style.pointerEvents = "none";
        list.style.pointerEvents = "auto";
    }

    return {
        hideDropdown,
        showDropdown
    }
})();