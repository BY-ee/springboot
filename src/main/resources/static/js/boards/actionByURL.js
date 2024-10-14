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
    for(let prop in lineText) {
        if(path.includes(prop)) {
            lineLink.attr('href', '/' + path).text(lineText[prop]);
            break;
        }
    }

    // 각 버튼별 경로 설정 객체
    const btnIDs = {
        '#go-to-main-page-btn': location.origin,  // 메인 페이지 이동 (/)
        '#go-to-login-page-btn': `/login`,  // 로그인 페이지 이동
        '#go-to-signup-page-btn': `/signup`,  // 회원가입 페이지 이동
        '#go-to-find-id-page-btn': `/find-id`,  // 아이디 찾기 페이지 이동
        '#go-to-find-pw-page-btn': `/find-pw`,  // 비밀번호 찾기 페이지 이동
        
        '#go-to-posts-btn': `/${path}`,  // 게시판 페이지 이동
        '#go-to-write-page-btn': `/${path}/write`  // 글 작성 페이지 이동
    };

    // 버튼 클릭시 경로 이동 이벤트 핸들러
    for(let btnID in btnIDs) {
        $(btnID).on('click', () => {
            location.href = btnIDs[btnID];
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