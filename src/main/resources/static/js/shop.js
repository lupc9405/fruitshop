// 頁面載入後動態抓商品資料
window.onload = async function() {

	const productTable = document.getElementById("productTableBody");
	const productInputs = document.getElementById("productInputs");

	const res = await fetch("/api/products");
	const products = await res.json();

	// 左邊商品列表
	products.forEach(p => {
		productTable.insertAdjacentHTML("beforeend", `
            <tr>
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>${p.unitName}</td>
            </tr>
        `);
	});

	// 右邊動態輸入欄位
	products.forEach(p => {
		productInputs.insertAdjacentHTML("beforeend", `
            <div class="form-group">
                <label>${p.name}：</label>
                <input type="number" data-id="${p.id}" min="0" max="10" value="0">
            </div>
        `);
	});
};

// 送出下單 API
async function submitOrder() {

    const customerName = document.getElementById("customerName").value.trim();
    if (!customerName) {
        alert("請輸入姓名");
        return;
    }

    const items = [];
    let invalidQty = false; // 數量超過 10 或負數

    document.querySelectorAll("input[data-id]").forEach(input => {
        const qty = Number(input.value);

        if (qty < 0 || qty > 10) invalidQty = true;
        if (qty > 0 && qty <= 10) {
            items.push({
                productId: Number(input.dataset.id),
                qty: qty
            });
        }
    });

    if (invalidQty) {
        alert("每項商品數量必須介於 0～10");
        return;
    }

    if (items.length === 0) {
        alert("請至少選擇一項商品");
        return;
    }

    const payload = { customerName, items };

    const response = await fetch("/api/orders", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });

    const result = await response.json();

    // 失敗不跳頁
    if (!result.success) {
        alert(result.message.replace(/<br>/g, "\n"));
        return;
    }

    // 成功跳結果頁
    localStorage.setItem("orderResult", JSON.stringify(result));
    window.location.href = "/order-result";
}