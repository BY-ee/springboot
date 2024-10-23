function toggleMenu() {
    $('.header-profile-menu').toggle();
    console.log('Toggle');
}

function hideMenu() {
    $('.header-profile-menu').hide();
    console.log('Hide');
}

$(() => {
    $('.setting-toggle').on('click', (e) => {
        e.stopPropagation();
        toggleMenu();
    });

    $(document).on('click', (e) => {
        if(!$(e.target).closest('.header-profile-menu').length) {
            hideMenu();
        }
    });
});