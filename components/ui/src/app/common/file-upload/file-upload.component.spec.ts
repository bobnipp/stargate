import {ComponentFixture, TestBed} from '@angular/core/testing'
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import {ClickOutsideModule} from 'ng-click-outside'
import {FileUploadComponent} from "./file-upload.component";
import {FileDetails} from "../../models/record-activity.model";

describe('FileUploadComponent', () => {
    let fixture: ComponentFixture<FileUploadComponent>
    let component: FileUploadComponent

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [
                FileUploadComponent
            ],
            providers: [],
            imports: [
                FormsModule,
                ReactiveFormsModule,
                ClickOutsideModule
            ]
        })

        fixture = TestBed.createComponent(FileUploadComponent)
        component = fixture.componentInstance
    })

    it('updates parent component on file add', () => {
        const fileContentsEncodedInHex = ["\x45\x6e\x63\x6f\x64\x65\x49\x6e\x48\x65\x78\x42\x65\x63\x61\x75\x73\x65\x42\x69\x6e\x61\x72\x79\x46\x69\x6c\x65\x73\x43\x6f\x6e\x74\x61\x69\x6e\x55\x6e\x70\x72\x69\x6e\x74\x61\x62\x6c\x65\x43\x68\x61\x72\x61\x63\x74\x65\x72\x73"];
        const blob = new Blob(fileContentsEncodedInHex);
        blob['name'] = 'test.txt'
        const e = {target: {files: [blob]}};
        const loadingEmitSpy = spyOn(component.fileLoading, 'emit')
        const fileAddEmitSpy = spyOn(component.fileAdd, 'emit')

        component.fileChange(e)

        expect(loadingEmitSpy).toHaveBeenCalledWith(true)

        component.processingComplete(component.getFileReader(), blob)()
        expect(loadingEmitSpy).toHaveBeenCalledWith(false)
        expect(fileAddEmitSpy).toHaveBeenCalledWith({name: 'test.txt', sizeString: '57 B', size: 57, bytes: ''} as FileDetails)
    })

})