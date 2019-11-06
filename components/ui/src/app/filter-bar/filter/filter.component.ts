import {Component, Input, OnInit} from '@angular/core'
import {FilterOption} from '../../models/select-option.model'
import {SystemOption} from '../../models/system-option.model'
import {TitleCasePipe} from '@angular/common'
import {FilterService} from '../../services/filter.service'
import {ClearableFilterComponent} from './clearable-filter.component'
import {SelectedFilterOptionState} from "../../models/record-filter-state.model";

@Component({
    selector: 'stargate-filter',
    templateUrl: './filter.component.html',
    styleUrls: ['./filter.component.scss']
})
export class FilterComponent implements OnInit, ClearableFilterComponent {

    @Input()
    availableOptions: SystemOption[]

    @Input()
    filterName: string

    @Input()
    defaultButtonText: string

    @Input()
    systemOptions: FilterOption[]

    selectedOptions: FilterOption[]
    showFilterView: Boolean
    buttonText: string
    buttonClass: string

    uppercaseOptions = ['imm', 'prism', 'dsa', 'loc', 'eo', 'fmv', 'ir', 'sar']

    private titleCasePipe: TitleCasePipe
    private filterService: FilterService

    constructor(titleCasePipe: TitleCasePipe, filterService: FilterService) {
        this.titleCasePipe = titleCasePipe
        this.filterService = filterService
        this.selectedOptions = []
        this.showFilterView = false
    }

    ngOnInit() {
        this.filterService.storedFilters.subscribe((result: SelectedFilterOptionState) => {
            this.selectedOptions = result[this.filterName]
            this.setFilterSelectedEnabled()
            this.generateButtonTextAndClass()
        })

        this.setFilterSelectedEnabled()
        this.generateButtonTextAndClass()
    }

    ngOnChanges(changes) {
        this.setFilterSelectedEnabled()
    }

    toggleFilterView() {
        if (this.showFilterView) {
            this.resetFiltersAndClose()
        } else {
            this.showFilterView = true
            this.generateButtonTextAndClass()
        }
    }

    toggleFilter(filter: FilterOption) {
        if (!filter.isDisabled) {
            if (!!this.selectedOptions.find(selectedOption => selectedOption.id === filter.id)) {
                this.selectedOptions = this.selectedOptions.filter(selectedOption => selectedOption.id !== filter.id)
                filter.isSelected = false
            } else {
                this.selectedOptions = [...this.selectedOptions, filter]
                filter.isSelected = true
            }
        }
    }

    doFilter() {
        this.showFilterView = false
        this.updateFilters()
    }

    clearFiltersAndApply() {
        this.resetFilters()
        this.updateFilters()
    }

    resetFiltersAndClose() {
        this.selectedOptions = this.filterService.getFilterState(this.filterName)
        this.setFilterSelectedEnabled()
        this.showFilterView = false
        this.generateButtonTextAndClass()
    }

    updateFilters() {
        this.filterService.updateFilter(this.filterName, this.selectedOptions)
        this.generateButtonTextAndClass()
    }

    getOptionFormat(option: string) {
        if (this.uppercaseOptions.includes(option.toLowerCase())) {
            return option.toUpperCase()
        } else if (option.toLowerCase() === 'send to nsrp') {
            return 'Send To NSRP'
        } else if (option.toLowerCase() === 'send to rms') {
            return 'Send To RMS'
        } else {
            return this.titleCasePipe.transform(option)
        }
    }

    clearCallback() {
        this.resetFilters()
        this.showFilterView = false
        this.generateButtonTextAndClass()
        this.filterService.updateFilterDontApply(this.filterName, this.selectedOptions)
    }

    generateButtonTextAndClass() {
        this.generateButtonClass()
        this.generateButtonText()
    }

    private resetFilters() {
        this.selectedOptions = []
        this.availableOptions.forEach(optionSet => {
            optionSet.options.forEach(option => {
                option.isSelected = false
            })
        })
    }

    private setFilterSelectedEnabled() {
        this.availableOptions.forEach(optionSet => {
            optionSet.options.forEach(option => {
                option.isSelected = !!this.selectedOptions.find(selectedOption => selectedOption.id === option.id && (selectedOption.system === option.system || !option.system))
                option.isDisabled = !!(this.systemOptions && this.systemOptions.length > 0 && option.system &&
                    !this.systemOptions.find(systemOption => systemOption.system === option.system))
            })
        })
    }

    private generateButtonText() {
        this.buttonText = this.defaultButtonText

        if (this.selectedOptions.length === 1) {
            this.buttonText = this.selectedOptions[0].value
        } else if (this.selectedOptions.length > 1) {
            this.buttonText = `${this.defaultButtonText} • ${this.selectedOptions.length}`
        }
    }

    private generateButtonClass() {
        this.buttonClass = this.showFilterView ? 'filter__selected' : 'filter__unselected'
        if (this.selectedOptions.length > 0) {
            this.buttonClass = this.showFilterView ? 'filter__selected--active' : 'filter__unselected--active'
        }
    }
}
