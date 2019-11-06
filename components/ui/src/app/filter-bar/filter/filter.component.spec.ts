import {TestBed} from '@angular/core/testing'
import {ClickOutsideModule} from 'ng-click-outside'
import {FilterComponent} from './filter.component'
import {TitleCasePipe} from '@angular/common'
import {FilterService} from '../../services/filter.service'
import {RecordType} from "../../models/record.model";
import {MockFilterService} from "../../../test_helpers/mockFilterService";
import Spy = jasmine.Spy;

describe('FilterComponent', () => {
    let component: FilterComponent
    let mockFilterService: MockFilterService
    let updateFilterSpy: Spy

    beforeEach(() => {
        mockFilterService = new MockFilterService()

        TestBed.configureTestingModule({
            declarations: [FilterComponent],
            providers: [
                {provide: FilterService, useValue: mockFilterService},
                TitleCasePipe
            ],
            imports: [ClickOutsideModule]
        })

        component = TestBed.createComponent(FilterComponent).componentInstance
        updateFilterSpy = spyOn(mockFilterService, 'updateFilter')
    })

    describe('toggleFilter()', () => {
        it('toggles filter condition', () => {
            component.filterName = 'statusList'
            component.selectedOptions = []
            component.toggleFilter({id: 1, value: 'TO_DO', system: RecordType.RFI})
            expect(component.selectedOptions.length).toEqual(1)
            component.toggleFilter({id: 1, value: 'TO_DO', system: RecordType.RFI})
            expect(component.selectedOptions.length).toEqual(0)
            component.toggleFilter({id: 1, value: 'TO_DO', system: RecordType.RFI})
            expect(component.selectedOptions.length).toEqual(1)
        })
    })

    describe('doFilter()', () => {
        it('applies the filter', () => {
            component.filterName = 'statusList'
            component.selectedOptions = []
            component.toggleFilter({id: 1, value: 'TO_DO', system: RecordType.RFI})
            component.doFilter()

            expect(component.showFilterView).toBeFalsy()
            expect(updateFilterSpy).toHaveBeenCalledWith('statusList', [{
                id: 1,
                value: 'TO_DO',
                system: RecordType.RFI,
                isSelected: true
            }])
        })
    })

    describe('clearFiltersAndApply()', () => {
        it('clears and applies the filter', () => {
            component.filterName = 'statusList'

            component.availableOptions = [
                {
                    displayName: 'Rfi',
                    options: [
                        {'id': 1, value: 'To Do', system: RecordType.RFI},
                        {'id': 2, value: 'Working', system: RecordType.RFI},
                        {'id': 3, value: '', system: RecordType.RFI}
                    ]
                }
            ]

            component.selectedOptions = [
                {'id': 1, value: 'To Do', system: RecordType.RFI},
                {'id': 2, value: 'Working', system: RecordType.RFI},
                {'id': 3, value: '', system: RecordType.RFI}
            ]

            component.clearFiltersAndApply()
            expect(component.selectedOptions).toEqual([])
            expect(component.availableOptions).toEqual([
                {
                    displayName: 'Rfi',
                    options: [
                        {'id': 1, value: 'To Do', system: RecordType.RFI, isSelected: false},
                        {'id': 2, value: 'Working', system: RecordType.RFI, isSelected: false},
                        {'id': 3, value: '', system: RecordType.RFI, isSelected: false}
                    ]
                }
            ])
            expect(updateFilterSpy).toHaveBeenCalledWith('statusList', [])
        })
    })

    describe('resetFiltersAndClose()', () => {
        it('reset filters returns the filter conditions to the previous highlightState', () => {
            spyOn(mockFilterService, 'getFilterState').and.returnValue([{
                'id': 1,
                value: 'To Do',
                system: RecordType.RFI
            }])
            component.filterName = 'statusList'
            component.systemOptions = [{id: 1, value: 'test', system: RecordType.RFI}]

            component.availableOptions = [
                {
                    displayName: 'Rfi',
                    options: [
                        {'id': 1, value: 'To Do', system: RecordType.RFI, isSelected: true},
                        {'id': 2, value: 'Working', system: RecordType.RFI},
                        {'id': 3, value: '', system: RecordType.RFI}
                    ]
                }
            ]

            component.selectedOptions = [
                {'id': 1, value: 'To Do', system: RecordType.RFI},
                {'id': 2, value: 'Working', system: RecordType.RFI},
                {'id': 3, value: '', system: RecordType.RFI}
            ]

            component.resetFiltersAndClose()
            expect(component.selectedOptions).toEqual([{'id': 1, value: 'To Do', system: RecordType.RFI}])
            expect(component.availableOptions).toEqual([
                {
                    displayName: 'Rfi',
                    options: [
                        {'id': 1, value: 'To Do', system: RecordType.RFI, isDisabled: false, isSelected: true},
                        {'id': 2, value: 'Working', system: RecordType.RFI, isDisabled: false, isSelected: false},
                        {'id': 3, value: '', system: RecordType.RFI, isDisabled: false, isSelected: false}
                    ]
                }
            ])
        })
    })

    describe('generate', () => {
        it('the button text', () => {
            component.selectedOptions = []
            component.defaultButtonText = 'STATUS'
            component.filterName = 'statusList'
            component.generateButtonTextAndClass()

            expect(component.buttonText).toEqual(component.defaultButtonText)

            component.toggleFilter({id: 1, value: 'TO DO', system: RecordType.RFI})
            expect(component.buttonText).toEqual(component.defaultButtonText)
            component.doFilter()
            expect(component.buttonText).toEqual('TO DO')

            component.toggleFilter({id: 2, value: 'WORKING', system: RecordType.RFI})
            expect(component.buttonText).toEqual('TO DO')
            component.doFilter()
            expect(component.buttonText).toEqual('STATUS • 2')

            component.toggleFilter({id: 3, value: 'SOMETHING', system: RecordType.RFI})
            expect(component.buttonText).toEqual('STATUS • 2')
            component.doFilter()
            expect(component.buttonText).toEqual('STATUS • 3')
        })

        it('the button class', () => {
            component.showFilterView = false
            component.selectedOptions = []
            component.defaultButtonText = 'STATUS'
            component.filterName = 'statusList'

            component.generateButtonTextAndClass()
            expect(component.buttonClass).toEqual('filter__unselected')

            component.showFilterView = true
            component.generateButtonTextAndClass()
            component.toggleFilter({id: 1, value: 'TO DO', system: RecordType.RFI})
            expect(component.buttonClass).toEqual('filter__selected')

            component.doFilter()
            expect(component.showFilterView).toEqual(false)
            expect(component.buttonClass).toEqual('filter__unselected--active')

            component.showFilterView = true
            component.generateButtonTextAndClass()
            component.toggleFilter({id: 1, value: 'TO DO', system: RecordType.RFI})
            expect(component.buttonClass).toEqual('filter__selected--active')

            component.doFilter()
            expect(component.showFilterView).toEqual(false)
            expect(component.buttonClass).toEqual('filter__unselected')
        })
    })

    describe('getOptionFormat', () => {
        it('returns uppercase options for specific options', () => {
            let system1 = 'Imm'
            let system2 = 'pRism'

            let dsa = 'Dsa'
            let loc = 'lOc'

            expect(component.getOptionFormat(system1)).toEqual('IMM')
            expect(component.getOptionFormat(system2)).toEqual('PRISM')

            expect(component.getOptionFormat(dsa)).toEqual('DSA')
            expect(component.getOptionFormat(loc)).toEqual('LOC')
        })

        it('returns capitalized acronyms', () => {
            let option = 'send to nsrp'

            expect(component.getOptionFormat(option)).toEqual('Send To NSRP')
        })
        it('returns title case for all other options', () => {
            let option = 'this text does not matter'

            expect(component.getOptionFormat(option)).toEqual('This Text Does Not Matter')
        })

    })
})