import {TestBed} from '@angular/core/testing'
import {ClickOutsideModule} from 'ng-click-outside'
import {FilterComponent} from '../filter/filter.component'
import {TitleCasePipe} from '@angular/common'
import {FilterService} from '../../services/filter.service'
import {RecordType} from "../../models/record.model";
import {MockFilterService} from "../../../test_helpers/mockFilterService";
import {FilterSystemComponent} from "./filter-system.component";
import {FilterSystemOptionComponent} from "./filter-system-option/filter-system-option.component";

describe('FilterSystermComponent', () => {
    let component: FilterSystemComponent
    let mockFilterService: MockFilterService

    beforeEach(() => {
        mockFilterService = new MockFilterService()

        TestBed.configureTestingModule({
            declarations: [FilterSystemComponent, FilterComponent, FilterSystemOptionComponent],
            providers: [
                {provide: FilterService, useValue: mockFilterService},
                TitleCasePipe
            ],
            imports: [ClickOutsideModule]
        })

        component = TestBed.createComponent(FilterSystemComponent).componentInstance
    })

    it('emits an event when the filters are updated', () => {
        const updateSpy = spyOn(component.filterUpdate, 'emit')
        component.selectedOptions = [{system: RecordType.RFI, value: 'some val', id: 1}, {system: RecordType.RFI, value: 'some val 2', id: 2}]
        component.updateFilters()

        expect(updateSpy).toHaveBeenCalledWith([{system: RecordType.RFI, value: 'some val', id: 1}, {system: RecordType.RFI, value: 'some val 2', id: 2}])
    })
})