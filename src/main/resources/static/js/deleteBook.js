// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let bookId = document.getElementById('bookId').value;
        fetch(`/library/${bookId}/delete`, {
            method: 'GET'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.href="/library"
            });
    });
}