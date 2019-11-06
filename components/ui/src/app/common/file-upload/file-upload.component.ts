import {Component, EventEmitter, NgZone, Output, ViewChild} from '@angular/core'
import {FileDetails} from "../../models/record-activity.model";

@Component({
    selector: 'stargate-file-upload',
    templateUrl: './file-upload.component.html',
    styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent {
    @ViewChild('fileInput')
    fileInput: any

    @Output()
    fileAdd: EventEmitter<FileDetails> = new EventEmitter<FileDetails>()

    @Output()
    fileLoading: EventEmitter<boolean> = new EventEmitter<boolean>()

    fileReader: FileReader

    constructor(private ngZone: NgZone) {}

    fileChange(event) {
        if (event.target.files && event.target.files.length) {
            this.processFileForUpload(event.target.files[0])
        }
    }

    getFileReader(): FileReader {
        if (!this.fileReader) {
            this.fileReader = new FileReader()
        }

        return this.fileReader
    }

    processFileForUpload(file) {
        const reader = this.getFileReader()

        this.fileLoading.emit(true)
        reader.readAsDataURL(file)

        reader.onload = () => {
            this.ngZone.run(this.processingComplete(reader, file))
        }
    }

    processingComplete(reader, file) {
        return () => {
            this.getFileReader()
            this.fileAdd.emit({
                bytes: reader.result,
                name: file.name,
                sizeString: this.buildFileSizeString(file.size),
                size: file.size
            })
            this.fileLoading.emit(false)
            this.fileInput.nativeElement.value = ''
        }
    }

    private buildFileSizeString(size: number): string {
        const kilobytes = size / 1000
        const megabytes = size / 1000000
        if (kilobytes <= 1) {
            return `${size} B`
        } else if (megabytes <= 1) {
            return `${Math.floor(kilobytes)} kB`
        }

        return `${megabytes.toFixed(1)} mB`
    }

}
