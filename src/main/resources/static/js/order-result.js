window.onload = function() {
	const data = localStorage.getItem("orderResult");

	if (!data) {
		document.getElementById("title").innerText = "沒有訂單資料";
		return;
	}

	const result = JSON.parse(data);

	// 設定標題
	const title = document.getElementById("title");
	if (result.success) {
		title.innerText = "下單成功";
		title.classList.add("success");
	} else {
		title.innerText = "下單失敗";
		title.classList.add("fail");
	}

	// 訊息（允許 <br>）
	document.getElementById("message").innerHTML = result.message;
};