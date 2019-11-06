import {Component, Input} from '@angular/core'
import {FormArray, FormControl, FormGroup} from '@angular/forms'
import {RecordSelectOptions} from '../../models/select-option.model'
import {RecordService} from "../../services/record.service";
import {Sensor} from "../../models/sensor.model";

@Component({
    selector: 'stargate-sidebar-sensor',
    templateUrl: './sidebar-sensor.component.html',
    styleUrls: ['../sidebar.component.scss', '../form.scss', '/sidebar-sensor.component.scss']
})
export class SidebarSensorComponent {

    @Input()
    recordSelectOptions: RecordSelectOptions

    @Input()
    rfiForm: FormGroup

    @Input()
    rfiId: number | string

    showMenu: number

    constructor(private recordService: RecordService) {}

    addSensor() {
        this.rfiFormArray().push(new FormGroup({
            id: new FormControl(''),
            rfiId: new FormControl(this.rfiForm.get('id').value),
            sensor: new FormControl(''),
            sensorType: new FormControl(''),
            mode: new FormControl(),
            desiredQuality: new FormControl(''),
            requiredQuality: new FormControl('')
        }))
    }

    openMenu(index: number) {
        this.showMenu = index
    }

    closeMenu() {
        this.showMenu = null
    }

    deleteSensor(index: number) {
        const sensor: Sensor = this.rfiFormArray().at(index).value
        this.rfiFormArray().removeAt(index)

        if (this.isExistingRfi()) {
            this.recordService.deleteRecordSensor(sensor)
        }
        this.showMenu = null
    }

    private isExistingRfi() {
        return this.rfiId != undefined
    }

    private rfiFormArray() {
        return this.rfiForm.get('sensors') as FormArray
    }

}
