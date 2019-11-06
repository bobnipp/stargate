import {Component, Input, OnInit} from '@angular/core'
import {FormGroup} from '@angular/forms'
import {AppState} from '../../state/appstate'
import {Store} from '@ngrx/store'
import {defaultSystemSelectOptions, RecordSelectOptions, SystemSelectOptions} from '../../models/select-option.model'
import {ToggleRfiInfoSectionAction} from '../../state/actions'
import {RecordItem} from '../../models/record.model'

@Component({
    selector: 'stargate-sidebar-details',
    templateUrl: './sidebar-details.component.html',
    styleUrls: ['./sidebar-details.component.scss', '../sidebar.component.scss', '../form.scss']
})
export class SidebarDetailsComponent implements OnInit {
    recordSelectOptions: RecordSelectOptions
    activeRfiSubForm: string
    showDetails: boolean = true

    @Input()
    rfiForm: FormGroup

    @Input()
    submitted: boolean

    @Input()
    record: RecordItem

    constructor(private store: Store<AppState>) {
        this.recordSelectOptions = defaultSystemSelectOptions()[0].data
    }

    toggle(section: string) {
        this.store.dispatch(new ToggleRfiInfoSectionAction(section))
    }

    toggleDetailsView() {
        this.showDetails = !this.showDetails
    }

    ngOnInit(): void {
        this.store.select('recordSelectOptions').subscribe((options: SystemSelectOptions[]) => {
            const optionSet = options.find(optionSet => optionSet.type === this.record.recordType)
            if (optionSet) {
                this.recordSelectOptions = optionSet.data
            }
        })

        this.store.select('activeRfiSubForm').subscribe((activeRfiSubForm: string) => {
            this.activeRfiSubForm = activeRfiSubForm
        })
    }
}