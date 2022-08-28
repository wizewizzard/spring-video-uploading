console.log("HEllo");

const videoPlayer = document.getElementById("videoPlayer");
const videoList = document.getElementById("videoList");
const videoForm = document.getElementById("videoUploadForm");

const queryParams = Object.fromEntries(new URLSearchParams(window.location.search));

console.log(queryParams);

const fetchVideoList = () => {
    const url = "/api/video/list";
    fetch(url)
        .then(resp => resp.json())
        .then(result => {
            videoList.innerHTML = "";
            result.forEach(v => {
                const li = document.createElement("li");
                const link = document.createElement("a");
                link.href = window.location.origin + "?video=" + v;
                link.innerText = v;
                li.appendChild(link);
                videoList.appendChild(li);
            })
        });
}

const setVideoSourceForPlayer = (videoName) => {
    videoPlayer.src = `/api/video/${videoName}`;
    videoPlayer.style.display = "block";
    document.getElementById("playerMessage").innerText = `You are watching ${videoName}`
}

const submitVideoUpload = (event) => {
    event.preventDefault();
    console.log("form data: ", event.target.elements.name.value);
    const data = new FormData();
    data.append("name", event.target.elements.name.value);
    data.append("file", event.target.elements.file.files[0]);
    fetch('/api/video', {
        method: 'POST',

        body: data
    }).then(result => result.text()).then(_ => {
        window.location.reload();
    })
        .catch(e => {
            console.log(e);
        });

    //let data = new FormData(form);
    //let data = new FormData(form);
}

videoForm.onsubmit = (event) => submitVideoUpload(event);
fetchVideoList();
if(queryParams.video){
    setVideoSourceForPlayer(queryParams.video);
}
else{
    videoPlayer.style.display = "none";
    document.getElementById("playerMessage").innerText = `Select a video`
}