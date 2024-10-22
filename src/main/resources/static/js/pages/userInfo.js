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
function activityPage() {
    const $userInfo = $('.user-info');
    $userInfo.empty();

    const pageHTML = `
        <div>
            <div class="ml-10p">
                <label for="email" class="pt-10">이메일 수정</label>
                <input class="width-50p mb-20" type="text" id="email" value="${user.email}" placeholder="이메일을 입력해주세요." />
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

$(() => {
    $('#profile-btn').on('click', (e) => {
        e.preventDefault();

        $.ajax({
            url: '/users/session',
            type: 'GET',
            success: (user) => {
                profilePage(user);
                history.replaceState(null, null, '/settings/profile');
            },
            error: (e) => {
                console.error(e);
            }
        });
    });  

    $('#account-settings-btn').on('click', (e) => {
        e.preventDefault();

        $.ajax({
            url: '/users/session',
            type: 'GET',
            success: (user) => {
                accountSettingPage(user);
                history.replaceState(null, null, '/settings/account');
            },
            error: (e) => {
                console.error(e);
            }
        });
    });

    $('#activity-btn').on('click', (e) => {
        e.preventDefault();

        $.ajax({
            url: '/',
            type: 'GET',
            success: (user) => {
                activityPage();
                history.replaceState(null, null, `/users/${user.id}/qna`);
            },
            error: (e) => {
                console.error(e);
            }
        });
    });

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