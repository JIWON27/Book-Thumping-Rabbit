// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let bookId = document.getElementById('reportId').value;
        fetch(`/report/${bookId}/delete`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.href = "/library";
            });
    });
}