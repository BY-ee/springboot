$(() => {
    // 현재 URL의 첫 번째 path 추출
    let path = location.pathname.split('/')[1];

    $('#go-to-write-page-btn').on('click', () => {
        location.href = path + '/write';
    });

    $('#go-to-posts-btn').on('click', () => {
        location.href = '/' + path;
    });

    $('#go-to-main-page-btn').on('click', () => {
        location.href = location.origin;
    });
});