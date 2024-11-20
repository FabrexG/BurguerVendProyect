const BOT_TOKEN = '7956475528:AAGr2QU9RanlqyS_6q7mcGpMVdQITE9wYx0';
const CHAT_ID = '1648418793';

function sendMessage() {
    const message = document.getElementById('message').value;
    const url = `https://api.telegram.org/bot${BOT_TOKEN}/sendMessage`;
    const data = {
        chat_id: CHAT_ID,
        text: message
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('message').value='';
        addMessageToChatLog('Tú',message);
    })
    .catch(error => {
        alert('Error al enviar el mensaje a Telegram',error);
    });
}
let lastUpdateId = 0;
function getUpdates() {
    const url = `https://api.telegram.org/bot${BOT_TOKEN}/getUpdates?offset=${lastUpdateId + 1}`;

    fetch(url)
    .then(response => response.json())
    .then(data => {
        data.result.forEach(update => {
            if(update.message) {
                const message = update.message.text;
                const username = update.message.from.username || 'Usuario';
                addMessageToChatLog(username,message);
                lastUpdateId = update.update_id;
            }
        });
    })
    .catch(error =>{
        console.error('Error',error);
    });
}

function addMessageToChatLog(username,message) {
    const chatLog = document.getElementById('chat-log');
    const messageElement = document.createElement('div');
    messageElement.textContent = `${username}: ${message}`;
    chatLog.appendChild(messageElement);
}

setInterval(getUpdates,5000);