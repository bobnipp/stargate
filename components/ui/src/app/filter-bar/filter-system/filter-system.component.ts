import {Component, EventEmitter, Output} from '@angular/core'
import {FilterComponent} from "../filter/filter.component";
import {TitleCasePipe} from "@angular/common";
import {FilterService} from "../../services/filter.service";
import {FilterOption} from "../../models/select-option.model";

@Component({
    selector: 'stargate-filter-system',
    templateUrl: './filter-system.component.html',
    styleUrls: ['./filter-system.component.scss', '../filter/filter.component.scss']
})
export class FilterSystemComponent extends FilterComponent {

    @Output()
    filterUpdate: EventEmitter<FilterOption[]> = new EventEmitter<FilterOption[]>()

    constructor(titleCasePipe: TitleCasePipe, filterService: FilterService) {
        super(titleCasePipe, filterService)
    }

    ngOnInit() {
        super.ngOnInit()
    }

    updateFilters() {
        super.updateFilters()
        this.filterUpdate.emit(this.selectedOptions)
    }

}
