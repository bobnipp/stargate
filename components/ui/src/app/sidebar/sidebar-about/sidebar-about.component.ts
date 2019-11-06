import {Component, Input, OnInit} from '@angular/core'
import {FormGroup} from '@angular/forms'
import {RecordSelectOptions} from '../../models/select-option.model'

@Component({
    selector: 'stargate-sidebar-about',
    templateUrl: './sidebar-about.component.html',
    styleUrls: ['../sidebar.component.scss', '../form.scss']
})
export class SidebarAboutComponent implements OnInit{

    @Input()
    recordSelectOptions: RecordSelectOptions

    @Input()
    rfiForm: FormGroup

    constructor() {
    }

    ngOnInit(): void {
    }

}
