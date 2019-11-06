import {Component, Input} from '@angular/core'
import {FormArray, FormControl, FormGroup} from '@angular/forms'
import {RecordSelectOptions} from '../../models/select-option.model'
import {RecordItem} from "../../models/record.model";

@Component({
    selector: 'stargate-sidebar-collection',
    templateUrl: './sidebar-collection.component.html',
    styleUrls: ['../sidebar.component.scss', '../form.scss', '/sidebar-collection.component.scss']
})
export class SidebarCollectionComponent {

    @Input()
    recordSelectOptions: RecordSelectOptions

    @Input()
    rfiForm: FormGroup

    @Input()
    record: RecordItem

    displayedMenuIndex: number = -1

    addEEI() {
        (this.rfiForm.get('eeis') as FormArray).push(new FormControl(''))
    }

    toggleMenu(index: number) {
        if (this.displayedMenuIndex < 0) {
            this.displayedMenuIndex = index
        } else {
            this.displayedMenuIndex = -1
        }
    }

    closeMenu() {
        this.displayedMenuIndex = -1
    }

    deleteEei(index: number) {
        const eeiControls = this.rfiForm.get('eeis') as FormArray
        const value = eeiControls.at(index).value

        eeiControls.removeAt(index)
        if (this.record.eeis.includes(value)) {
            this.rfiForm.markAsDirty()
        }
        this.displayedMenuIndex = -1
    }

}
