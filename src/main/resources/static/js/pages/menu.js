function ToggleMenu() {
    $('.header-profile-menu').toggle();
    console.log('Toggle');
}

function HideMenu() {
    $('.header-profile-menu').hide();
    console.log('Hide');
}

$(() => {
    $('.header-profile').on('click', () => {
        ToggleMenu();
    });

    $(document).click((e) => {
        if(!$(e.target).closest('.header-profile-menu').length) {
            // HideMenu();
        }
    });
});