function calculateTimeDifference(createdAt) {
    let currentTime = new Date().getTime();
    
    console.log(`Created: ${createdAt}
Current: ${currentTime}
Difference: ${(currentTime - createdAt) / 1000} seconds`);

    return (currentTime - createdAt) / 1000;  // 초 단위 반환
}

function formatTimeDifference(timeDifference) {
    const timeUnits = {
        second: 60,
        minute: 3600,
        hour: 216000,
        day: 5184000,
        week: 36288000,
        month: 1088640000,
        year: Infinity
    };

    const translations = {
        second: '초',
        minute: '분',
        hour: '시간',
        day: '일',
        week: '주',
        month: '달',
        year: '년'
    }

    for (const key in timeUnits) {
        const time = timeUnits[key];
        
        if (timeDifference < time) {
            const count = Math.floor((timeDifference * 60) / (timeUnits[key]));
            $('#created-at-text').text(`약 ${count}${translations[key]} 전`);
            break;
        }
    }
}

$(() => {
    // 게시글 시간 표시
    let createdAt = $('.post-box').data('created-at');
    let timeDifference = calculateTimeDifference(createdAt);

    formatTimeDifference(timeDifference);    
});