import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { DevisUpdateComponent } from 'app/entities/devis/devis-update.component';
import { DevisService } from 'app/entities/devis/devis.service';
import { Devis } from 'app/shared/model/devis.model';

describe('Component Tests', () => {
  describe('Devis Management Update Component', () => {
    let comp: DevisUpdateComponent;
    let fixture: ComponentFixture<DevisUpdateComponent>;
    let service: DevisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [DevisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DevisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DevisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DevisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Devis(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Devis();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
