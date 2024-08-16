import { Component } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent {
  selectedFiles: File[] = [];

  constructor(private http: HttpClient) {}

  onFileSelected(event: any) {
    this.selectedFiles = Array.from(event.target.files);
  }

  onUpload() {
    if (this.selectedFiles.length > 0) {
      const formData = new FormData();
      this.selectedFiles.forEach(file => {
        formData.append('files', file, file.name);
      });

      const url = '/api/files/upload';

      this.http.post(url, formData, {
        reportProgress: true,
        observe: 'events'
      }).subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          const progress = Math.round(100 * (event.loaded / (event.total || 1)));
          console.log(`File is ${progress}% uploaded.`);
        } else if (event.type === HttpEventType.Response) {
          console.log('Files are completely uploaded!', event.body);
          alert("Yükleme Başarılı. ♥ Teşekkür Ederiz ♥")
        }
      }, error => {
        console.error('Upload failed', error);
      });
    }
  }
}
