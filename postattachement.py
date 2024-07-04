import requests
import os

task_id = "your_task_id"
url = f"https://api.clickup.com/api/v2/task/{task_id}/attachment"

query = {
    "custom_task_ids": "86epr4har",
    "team_id": "123"
}

headers = {
    "Authorization": "Bearer pk_96750405_C4N3NNHPNME1JZDBM5VGV6KVASB6H74D"
}

# Path ke semua file yang ada di dalam folder screenshots
all_files = os.listdir("screenshots")
# Mengambil file yang memiliki ekstensi .png
png_files = [file for file in all_files if file.endswith(".png")]

for file_name in png_files:
    file_path = os.path.join("screenshots", file_name)

    # Membuka file dalam mode biner
    with open(file_path, "rb") as file:
        files = {
            "attachment": (file_name, file, "image/png")
        }

        # Mengirimkan permintaan POST ke API ClickUp
        response = requests.post(url, headers=headers, files=files, params=query)

        # Memeriksa dan mencetak respon dari API
        if response.status_code == 200:
            print(f"File {file_name} uploaded successfully.")
        else:
            print(f"Failed to upload file {file_name}. Status code: {response.status_code}")
            print(response.json())
