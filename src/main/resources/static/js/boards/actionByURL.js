$(() => {
    // 각 게시판 별 line 템플릿의 텍스트
    const lineText = {
        qna: 'QnA',
        community: 'Community'
    }

    // 현재 URL의 첫 번째 path 추출
    const path = location.pathname.split('/')[1];

    // line 템플릿의 링크 & 텍스트 설정
    const lineLink = $('#line-span').find('a');
    if(path.includes('qna') || path.includes('community')) {
        lineLink.attr('href', '/' + path).text(lineText[path]);
    }

    // 글 작성 페이지 이동 버튼 이벤트 핸들러
    $('#go-to-write-page-btn').on('click', () => {
        location.href = '/' + path + '/write';
    });

    // 게시판 이동 버튼 이벤트 핸들러
    $('#go-to-posts-btn').on('click', () => {
        location.href = '/' + path;
    });

    // 메인 페이지 이동 버튼 이벤트 핸들러
    $('#go-to-main-page-btn').on('click', () => {
        location.href = location.origin;
    });

    // 글 작성 버튼 이벤트 핸들러
    $('#write-post-btn').on('click', () => {
        const title = $('#title').val();
        const content = $('#content').val();
        const topic = $('#topic').val();
        const tag = $('#tag').val();

        const post = {
            title: title,
            content: content,
            topic: topic,
            tag: tag
        };

        $.ajax({
            url: '/' + path + '/write',
            type: 'POST',
            data: JSON.stringify(post),
            contentType: 'application/json',
            success: () => {
                console.log('Success');
            },
            error: (e) => {
                console.error(e);
            }
        });
    });
});