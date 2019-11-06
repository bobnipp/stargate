import {Component, Input} from '@angular/core'
import {FormArray, FormControl, FormGroup} from '@angular/forms'
import {RecordSelectOptions} from '../../models/select-option.model'
import {CoordinatesService} from '../../services/coordinates.service'
import {Target} from '../../models/target.model'
import {RecordService} from '../../services/record.service'
import {CoordinateValidators} from '../../validators/custom-validators'

declare var require: any //TODO: can we find an alternative to this?

@Component({
    selector: 'stargate-sidebar-target',
    templateUrl: './sidebar-target.component.html',
    styleUrls: ['../sidebar.component.scss', '../form.scss', '/sidebar-target.component.scss']
})
export class SidebarTargetComponent {

    @Input()
    recordSelectOptions: RecordSelectOptions

    @Input()
    rfiForm: FormGroup

    @Input()
    rfiId: number | string

    showMenu: number
    showPopover: string = ''

    invalidNumberOfCoords: string
    invalidCoords: string
    invalidBasicCoords: string

    constructor(private coordinatesService: CoordinatesService, private recordService: RecordService) {
        this.invalidBasicCoords = CoordinateValidators.INVALID_BASIC_COORDINATES_MESSAGE
        this.invalidCoords = CoordinateValidators.INVALID_COORDINATES_MESSAGE
        this.invalidNumberOfCoords = CoordinateValidators.INVALID_NUMBER_OF_COORDINATES_MESSAGE
    }

    addTarget() {
        (this.rfiForm.get('targets') as FormArray).push(new FormGroup({
            id: new FormControl(''),
            rfiId: new FormControl(this.rfiForm.get('id').value),
            name: new FormControl(''),
            type: new FormControl(''),
            radius: new FormControl(),
            radiusUnit: new FormControl('NM'),
            coordinates: new FormControl('', {
                validators: [CoordinateValidators.validateCoordinates],
                updateOn: 'blur'
            })
        }, {validators: CoordinateValidators.validateTargetCoordinates, updateOn: 'blur'}))
    }

    openMenu(index: number) {
        this.showMenu = index
    }

    closeMenu() {
        this.showMenu = null
    }

    deleteTarget(index: number) {
        const target: Target = this.rfiFormArray().at(index).value
        this.rfiFormArray().removeAt(index)

        if (this.isExistingRfi()) {
            this.recordService.deleteRecordTarget(target)
        }
        this.showMenu = null
    }

    setPopover(id: string) {
        if (this.showPopover === id) {
            this.showPopover = ''
        } else {
            this.showPopover = id
        }
    }

    private isExistingRfi() {
        return this.rfiId != undefined
    }

    private rfiFormArray() {
        return this.rfiForm.get('targets') as FormArray
    }
}
