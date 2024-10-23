// 프로필 페이지 동적 렌더링
function profilePage(user) {
    const $userInfo = $('.user-info');
    $userInfo.empty();

    const pageHTML = `
        <div class="flex">
            <div class="ml-15p mt-5vh">
                <div class="flex-space-between al-items-f-start">
                    <div class="width-40vh flex-grow-1">
                        <div>
                            <span class="font-white" style="margin-left: 3px;">아이디</span><br/>
                            <span class="width-100p ml-5 font-white" type="text" id="user-id">${user.userId}</span>

                            <label for="email" class="pt-10">이메일</label>
                            <input class="width-100p mb-10" type="text" id="email" value="${user.email}" placeholder="이메일을 입력해주세요." />
                        
                            <label for="nickname">닉네임</label>
                            <input class="width-100p" type="text" id="nickname" value="${user.nickname}" placeholder="닉네임을 입력해주세요." />
                        
                            <div class="button-container mt-40">
                                <button class="btn btn-primary" type="button" id="update-user-btn">저장</button>
                                <button class="btn btn-primary" type="button" id="go-to-main-page-btn">돌아가기</button>
                            </div>
                        </div>
                    </div>

                    <div class="flex-shrink-0 ml-20" style="margin-left: 200px;">
                        <img class="profile-img width-100 height-100 pointer"
                        src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/React-icon.svg/512px-React-icon.svg.png" />
                    </div>
                </div>
            </div>
        </div>
    `;

    $userInfo.html(pageHTML);
}

// 계정 설정 페이지 동적 렌더링
function accountSettingPage(user) {
    const $userInfo = $('.user-info');
    $userInfo.empty();

    const pageHTML = `
        <div>
            <div class="ml-10p">
                <label for="email" class="pt-10">이메일 수정</label>
                <input class="width-50p mb-20" type="text" id="email" value="${user.email}" placeholder="이메일을 입력해주세요." />
                <button class="btn btn-dark ml-20" type="button" id="authn-phone">이메일 인증</button>
                <p>이메일 인증 요청 시 입력하신 이메일로 인증 요청 메일이 발송됩니다.</p>
                <p>이메일 인증이 정상적으로 완료된 후에 이메일이 수정됩니다.</p>
            </div>
            <div class="settings-separator"></div>
        </div>

        <div>
            <div class="ml-10p">
                <label for="email" class="pt-10">휴대폰 인증</label>
                <button class="btn btn-dark mb-10" type="button">휴대폰 인증하기</button>
            </div>
            <div class="settings-separator"></div>
        </div>
    `;

    $userInfo.html(pageHTML);
}

// 활동내역 페이지 동적 렌더링
function activityPage(pagination) {
    const $userInfo = $('.user-info');
    $userInfo.empty();

    const posts = pagination.posts;

    let pageHTML = `
        <div>
        </div>
        <div class="flex px-50 mb-30 gap-20 font-size-24">
            <a>QnA</a>
            <a>커뮤니티</a>
        </div>
    `;

    posts.forEach(post => {
        pageHTML += `
            <div class="px-50 mt-20">
                <div class="flex-space-between al-items-center">
                    <div>
                        <span class="border-white px-10 py-5 text-center font-white" style="border-radius: 0.7rem;">${post.topic}</span>
                        <a class="ml-5">tag: ${post.tag}</a>
                    </div>
                    <span class="font-white">작성일 ${post.createdAt}</span>
                </div>

                <div class="flex-space-between al-items-center border-bottom-white py-10">
                    <p class="font-size-18"><b>${post.title}</b></p>
                    <span class="font-white" style="margin-top: -25px;">최종 수정일 ${post.updatedAt}</span>
                </div>
            </div>
        `;
    });

    $userInfo.html(pageHTML);
}

// 세션의 유저 데이터 요청
function fetchSessionUser(successCallback) {
    $.ajax({
        url: '/users/session',
        type: 'GET',
        success: (user) => {
            successCallback(user);
        },
        error: (e) => {
            console.error(e);
        }
    });
}

// 세션의 유저 id 데이터 요청
function fetchSessionId(successCallback) {
    $.ajax({
        url: '/users/session/id',
        type: 'GET',
        success: (id) => {
            successCallback(id);
        },
        error: (e) => {
            console.error(e);
        }
    });
}

// 유저 id로 게시글 데이터 조회
function fetchQnAPostsByUserId(userId, successCallback) {
    $.ajax({
        url: `/users/${userId}/qna`,
        type: 'GET',
        success: (pagination) => {
            successCallback(pagination);
        },
        error: (e) => {
            console.error(e);
        }
    });
}

function fetchCommunityPostsByUserId(userId, successCallback) {
    $.ajax({
        url: `/users/${userId}/community`,
        type: 'GET',
        success: (pagination) => {
            successCallback(pagination);
        },
        error: (e) => {
            console.error(e);
        }
    });
}


$(() => {
    /*               *
    *    Function    *
    *                */

    // 프로필 버튼 클릭 이벤트 핸들러
    $('#profile-btn').on('click', (e) => {
        e.preventDefault();

        fetchSessionUser((user) => {
            profilePage(user);
            history.replaceState(null, null, '/profile');
        });
    });  

    // 계정 설정 버튼 클릭 이벤트 핸들러
    $('#account-settings-btn').on('click', (e) => {
        e.preventDefault();

        fetchSessionUser((user) => {
            accountSettingPage(user);
            history.replaceState(null, null, '/account');
        });
    });

    // 활동내역 버튼 클릭 이벤트 핸들러
    $('#activity-btn').on('click', (e) => {
        e.preventDefault();

        const pattern = /\/users\/\d+\/\w+/;

        // uri가 정규표현식 형식에 맞지 않을 시 실행
        if(!pattern.test(location.pathname)) {
            fetchSessionId((id) => {
                history.replaceState(null, null, `/users/${id}/qna`);

                fetchQnAPostsByUserId(id, (pagination) => {
                    activityPage(pagination);
                })
            })
        // uri가 정규표현식 형식에 맞을 경우 실행
        } else {
            const userId = location.pathname.split('/')[2];
    
            fetchQnAPostsByUserId(userId, (pagination) => {
                activityPage(pagination);
            })
        }
    });

    
    /*             *
    *    Button    *
    *              */

    // 돌아가기 버튼
    $(document).on('click', '#go-to-main-page-btn', () => {
        location.href = location.origin;
    });

    // 유저 정보 수정 이벤트 핸들러
    $(document).on('click', '#update-user-btn', () => {
        const email = $('#email').val();
        const nickname = $('#nickname').val();

        $.ajax({
            url: '/user/update',
            type: 'POST',
            data: JSON.stringify({ email, nickname }),
            contentType: 'application/json',
            success: (response) => {
                console.log(response);
            },
            error: (e) => {
                console.error(e);
            }
        });
    });
});